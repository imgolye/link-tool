package org.springlink.core.boot.tenant;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springlink.core.mp.config.MybatisPlusConfiguration;

/**
 * 多租户配置类
 *
 * @author GL
 */
@Configuration(proxyBeanMethods = false)
@AllArgsConstructor
@AutoConfigureBefore(MybatisPlusConfiguration.class)
@EnableConfigurationProperties(LinkTenantProperties.class)
public class TenantConfiguration {

	/**
	 * 自定义多租户处理器
	 *
	 * @param tenantProperties 多租户配置类
	 * @return TenantHandler
	 */
	@Bean
	@Primary
	public TenantLineHandler linkTenantHandler(LinkTenantProperties tenantProperties) {
		return new LinkTenantHandler(tenantProperties);
	}

	/**
	 * 自定义租户拦截器
	 *
	 * @param tenantHandler 多租户处理器
	 * @return LinkTenantInterceptor
	 */
	@Bean
	@Primary
	public TenantLineInnerInterceptor tenantLineInnerInterceptor(TenantLineHandler tenantHandler) {
		LinkTenantInterceptor tenantInterceptor = new LinkTenantInterceptor();
		tenantInterceptor.setTenantLineHandler(tenantHandler);
		return tenantInterceptor;
	}

	/**
	 * 自定义租户id生成器
	 *
	 * @return TenantId
	 */
	@Bean
	@ConditionalOnMissingBean(TenantId.class)
	public TenantId tenantId() {
		return new LinkTenantId();
	}

}
