package org.springlink.core.swagger;


import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springlink.core.launch.properties.LinkProperties;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * swagger配置
 *
 * @author Gl
 */
@Configuration(proxyBeanMethods = false)
@org.springlink.core.swagger.EnableSwagger
@EnableConfigurationProperties(SwaggerProperties.class)
@Import(BeanValidatorPluginsConfiguration.class)
@AllArgsConstructor
public class SwaggerAutoConfiguration {

	private static final String DEFAULT_BASE_PATH = "/**";
	private static final List<String> DEFAULT_EXCLUDE_PATH = Arrays.asList("/error", "/actuator/**");

	/**
	 * 引入Knife4j扩展类
	 */
	private final OpenApiExtensionResolver openApiExtensionResolver;

	/**
	 * 引入Link环境变量
	 */
	private final LinkProperties linkProperties;

	@Bean
	@ConditionalOnMissingBean
	public SwaggerProperties swaggerProperties() {
		return new SwaggerProperties();
	}

	@Bean
	public Docket api(SwaggerProperties swaggerProperties) {
		// base-path处理
		if (swaggerProperties.getBasePath().size() == 0) {
			swaggerProperties.getBasePath().add(DEFAULT_BASE_PATH);
		}

		// exclude-path处理
		if (swaggerProperties.getExcludePath().size() == 0) {
			swaggerProperties.getExcludePath().addAll(DEFAULT_EXCLUDE_PATH);
		}
		ApiSelectorBuilder apis = new Docket(DocumentationType.SWAGGER_2)
			.host(swaggerProperties.getHost())
			.apiInfo(apiInfo(swaggerProperties)).select()
			.apis(org.springlink.core.swagger.SwaggerUtil.basePackages(swaggerProperties.getBasePackages()));

		swaggerProperties.getBasePath().forEach(p -> apis.paths(PathSelectors.ant(p)));
		swaggerProperties.getExcludePath().forEach(p -> apis.paths(PathSelectors.ant(p).negate()));

		return apis.build()
			.securitySchemes(Collections.singletonList(securitySchema(swaggerProperties)))
			.securityContexts(Collections.singletonList(securityContext(swaggerProperties)))
			.securityContexts(Lists.newArrayList(securityContext(swaggerProperties)))
			.securitySchemes(Collections.singletonList(securitySchema(swaggerProperties)))
			.extensions(openApiExtensionResolver.buildExtensions(linkProperties.getName()));
	}

	/**
	 * 配置默认的全局鉴权策略的开关，通过正则表达式进行匹配；默认匹配所有URL
	 *
	 * @return
	 */
	private SecurityContext securityContext(SwaggerProperties swaggerProperties) {
		return SecurityContext.builder()
			.securityReferences(defaultAuth(swaggerProperties))
			.forPaths(PathSelectors.regex(swaggerProperties.getAuthorization().getAuthRegex()))
			.build();
	}

	/**
	 * 默认的全局鉴权策略
	 *
	 * @return
	 */
	private List<SecurityReference> defaultAuth(SwaggerProperties swaggerProperties) {
		ArrayList<AuthorizationScope> authorizationScopeList = new ArrayList<>();
		swaggerProperties.getAuthorization().getAuthorizationScopeList().forEach(authorizationScope -> authorizationScopeList.add(new AuthorizationScope(authorizationScope.getScope(), authorizationScope.getDescription())));
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[authorizationScopeList.size()];
		return Collections.singletonList(SecurityReference.builder()
			.reference(swaggerProperties.getAuthorization().getName())
			.scopes(authorizationScopeList.toArray(authorizationScopes))
			.build());
	}


	private OAuth securitySchema(SwaggerProperties swaggerProperties) {
		ArrayList<AuthorizationScope> authorizationScopeList = new ArrayList<>();
		swaggerProperties.getAuthorization().getAuthorizationScopeList().forEach(authorizationScope -> authorizationScopeList.add(new AuthorizationScope(authorizationScope.getScope(), authorizationScope.getDescription())));
		ArrayList<GrantType> grantTypes = new ArrayList<>();
		swaggerProperties.getAuthorization().getTokenUrlList().forEach(tokenUrl -> grantTypes.add(new ResourceOwnerPasswordCredentialsGrant(tokenUrl)));
		return new OAuth(swaggerProperties.getAuthorization().getName(), authorizationScopeList, grantTypes);
	}

	private ApiInfo apiInfo(SwaggerProperties swaggerProperties) {
		return new ApiInfoBuilder()
			.title(swaggerProperties.getTitle())
			.description(swaggerProperties.getDescription())
			.license(swaggerProperties.getLicense())
			.licenseUrl(swaggerProperties.getLicenseUrl())
			.termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())
			.contact(new Contact(swaggerProperties.getContact().getName(), swaggerProperties.getContact().getUrl(), swaggerProperties.getContact().getEmail()))
			.version(swaggerProperties.getVersion())
			.build();
	}

}
