package org.springlink.core.datascope.config;
import lombok.AllArgsConstructor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springlink.core.datascope.handler.LinkDataScopeHandler;
import org.springlink.core.datascope.handler.LinkScopeModelHandler;
import org.springlink.core.datascope.handler.DataScopeHandler;
import org.springlink.core.datascope.handler.ScopeModelHandler;
import org.springlink.core.datascope.interceptor.DataScopeInterceptor;
import org.springlink.core.datascope.props.DataScopeProperties;

/**
 * 数据权限配置类
 *
 * @author GL
 */
@Configuration(proxyBeanMethods = false)
@AllArgsConstructor
@EnableConfigurationProperties(DataScopeProperties.class)
public class DataScopeConfiguration {

	private final JdbcTemplate jdbcTemplate;

	@Bean
	@ConditionalOnMissingBean(ScopeModelHandler.class)
	public ScopeModelHandler scopeModelHandler() {
		return new LinkScopeModelHandler(jdbcTemplate);
	}

	@Bean
	@ConditionalOnBean(ScopeModelHandler.class)
	@ConditionalOnMissingBean(DataScopeHandler.class)
	public DataScopeHandler dataScopeHandler(ScopeModelHandler scopeModelHandler) {
		return new LinkDataScopeHandler(scopeModelHandler);
	}

	@Bean
	@ConditionalOnBean(DataScopeHandler.class)
	@ConditionalOnMissingBean(DataScopeInterceptor.class)
	public DataScopeInterceptor interceptor(DataScopeHandler dataScopeHandler, DataScopeProperties dataScopeProperties) {
		return new DataScopeInterceptor(dataScopeHandler, dataScopeProperties);
	}

}
