package org.springlink.core.swagger;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.Ordered;
import org.springlink.core.launch.constant.AppConstant;
import org.springlink.core.launch.service.LauncherService;

import java.util.Properties;

/**
 * 初始化Swagger配置
 *
 * @author GL
 */
public class SwaggerLauncherServiceImpl implements LauncherService {
	@Override
	public void launcher(SpringApplicationBuilder builder, String appName, String profile) {
		Properties props = System.getProperties();
		if (profile.equals(AppConstant.PROD_CODE)) {
			props.setProperty("knife4j.production", "true");
		}
		props.setProperty("knife4j.enable", "true");
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}
}
