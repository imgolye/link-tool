package org.springlink.core.launch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StringUtils;

/**
 * 项目启动事件通知
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class StartEventListener {

    @Async
    @Order
    @EventListener(WebServerInitializedEvent.class)
    public void afterStart(WebServerInitializedEvent event) {
        Environment environment = event.getApplicationContext().getEnvironment();
        String appName = environment.getProperty("spring.application.name").toUpperCase();
        int localPort = event.getWebServer().getPort();
        String profile = StringUtils.arrayToCommaDelimitedString(environment.getActiveProfiles());
        log.info("---[{}]---启动完成，当前使用的端口:[{}]，环境变量:[{}]---", appName, localPort, profile);
    }

    public static void main(String[] args){






        String str="select Fcoupon_id,Fuser_id from t_coupon_user_0 union";




        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {

        }

        long end = System.currentTimeMillis();

        long cost = end - start;

        System.out.println(str);


    }
}
