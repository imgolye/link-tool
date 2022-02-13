package org.springlink.core.launch.config;


import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springlink.core.launch.properties.LinkProperties;

/**
 * 配置类
 * @author Goly
 */
@Configuration(proxyBeanMethods = false)
@AllArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties({
        LinkProperties.class
})
public class LinkLaunchConfiguration {





}
