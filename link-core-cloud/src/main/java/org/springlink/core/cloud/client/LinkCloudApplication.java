package org.springlink.core.cloud.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springlink.core.launch.constant.AppConstant;

import java.lang.annotation.*;

/**
 * Cloud启动注解配置
 * @Author: Gol
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableDiscoveryClient
@EnableFeignClients(AppConstant.BASE_PACKAGES)
@SpringBootApplication
public @interface LinkCloudApplication {
}
