package org.springlink.core.log.config;

import lombok.AllArgsConstructor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springlink.core.launch.properties.LinkProperties;
import org.springlink.core.launch.server.ServerInfo;
import org.springlink.core.log.aspect.ApiLogAspect;
import org.springlink.core.log.event.ApiLogListener;
import org.springlink.core.log.event.ErrorLogListener;
import org.springlink.core.log.event.UsualLogListener;
import org.springlink.core.log.feign.ILogClient;
import org.springlink.core.log.logger.LinkLogger;

/**
 * 日志工具自动配置
 *
 * @author GL
 */
@Configuration(proxyBeanMethods = false)
@AllArgsConstructor
@ConditionalOnWebApplication
public class LinkLogToolAutoConfiguration {

	private final ILogClient logService;
	private final ServerInfo serverInfo;
	private final LinkProperties linkProperties;

	@Bean
	public ApiLogAspect apiLogAspect() {
		return new ApiLogAspect();
	}

	@Bean
	public LinkLogger linkLogger() {
		return new LinkLogger();
	}

	@Bean
	public ApiLogListener apiLogListener() {
		return new ApiLogListener(logService, serverInfo, linkProperties);
	}

	@Bean
	public ErrorLogListener errorEventListener() {
		return new ErrorLogListener(logService, serverInfo, linkProperties);
	}

	@Bean
	public UsualLogListener linkEventListener() {
		return new UsualLogListener(logService, serverInfo, linkProperties);
	}

}
