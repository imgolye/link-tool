package org.springlink.core.test;


import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.NonNull;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springlink.core.launch.LinkApplication;
import org.springlink.core.launch.constant.AppConstant;
import org.springlink.core.launch.constant.NacosConstant;
import org.springlink.core.launch.constant.SentinelConstant;
import org.springlink.core.launch.service.LauncherService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 设置启动参数
 * @author GL
 */
public class LinkSpringExtension extends SpringExtension {

	@Override
	public void beforeAll(@NonNull ExtensionContext context) throws Exception {
		super.beforeAll(context);
		setUpTestClass(context);
	}

	private void setUpTestClass(ExtensionContext context) {
		Class<?> clazz = context.getRequiredTestClass();
		LinkBootTest linkBootTest = AnnotationUtils.getAnnotation(clazz, LinkBootTest.class);
		if (linkBootTest == null) {
			throw new LinkBootTestException(String.format("%s must be @LinkBootTest .", clazz));
		}
		String appName = linkBootTest.appName();
		String profile = linkBootTest.profile();
		boolean isLocalDev = LinkApplication.isLocalDev();
		Properties props = System.getProperties();
		props.setProperty("link.env", profile);
		props.setProperty("link.name", appName);
		props.setProperty("link.is-local", String.valueOf(isLocalDev));
		props.setProperty("link.dev-mode", profile.equals(AppConstant.PROD_CODE) ? "false" : "true");
		props.setProperty("link.service.version", AppConstant.APPLICATION_VERSION);
		props.setProperty("spring.application.name", appName);
		props.setProperty("spring.profiles.active", profile);
		props.setProperty("info.version", AppConstant.APPLICATION_VERSION);
		props.setProperty("info.desc", appName);
		props.setProperty("spring.cloud.nacos.discovery.server-addr", NacosConstant.NACOS_ADDR);
		props.setProperty("spring.cloud.nacos.config.server-addr", NacosConstant.NACOS_ADDR);
		props.setProperty("spring.cloud.nacos.config.prefix", NacosConstant.NACOS_CONFIG_PREFIX);
		props.setProperty("spring.cloud.nacos.config.file-extension", NacosConstant.NACOS_CONFIG_FORMAT);
		props.setProperty("spring.cloud.sentinel.transport.dashboard", SentinelConstant.SENTINEL_ADDR);
		props.setProperty("spring.main.allow-bean-definition-overriding", "true");
		// 加载自定义组件
		if (linkBootTest.enableLoader()) {
			List<LauncherService> launcherList = new ArrayList<>();
			SpringApplicationBuilder builder = new SpringApplicationBuilder(clazz);
			ServiceLoader.load(LauncherService.class).forEach(launcherList::add);
			launcherList.stream().sorted(Comparator.comparing(LauncherService::getOrder)).collect(Collectors.toList())
				.forEach(launcherService -> launcherService.launcher(builder, appName, profile));
		}
		System.err.printf("---[junit.test]:[%s]---启动中，读取到的环境变量:[%s]%n", appName, profile);
	}

}
