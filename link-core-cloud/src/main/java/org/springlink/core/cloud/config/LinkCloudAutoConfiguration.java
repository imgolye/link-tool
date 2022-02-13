package org.springlink.core.cloud.config;

import com.alibaba.cloud.sentinel.feign.SentinelFeignAutoConfiguration;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import feign.Feign;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springlink.core.cloud.feign.LinkFeignSentinel;
import org.springlink.core.cloud.header.LinkFeignRequestHeaderInterceptor;
import org.springlink.core.cloud.sentinel.LinkBlockExceptionHandler;


/**
 *link  cloud 增强配置
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(SentinelFeignAutoConfiguration.class)
public class LinkCloudAutoConfiguration {

	@Bean
	@Scope("prototype")
	@ConditionalOnMissingBean
	@ConditionalOnProperty(name = "feign.sentinel.enabled")
	public Feign.Builder feignSentinelBuilder(RequestInterceptor requestInterceptor) {
		return LinkFeignSentinel.builder().requestInterceptor(requestInterceptor);
	}

	@Bean
	@ConditionalOnMissingBean
	public BlockExceptionHandler blockExceptionHandler() {
		return new LinkBlockExceptionHandler();
	}

	@Bean
	@ConditionalOnMissingBean
	public RequestInterceptor requestInterceptor() {
		return new LinkFeignRequestHeaderInterceptor();
	}

}
