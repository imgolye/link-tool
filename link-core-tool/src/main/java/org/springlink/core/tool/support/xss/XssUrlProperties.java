package org.springlink.core.tool.support.xss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Xss配置类
 *
 *
 */
@Data
@ConfigurationProperties("link.xss.url")
public class XssUrlProperties {

	private final List<String> excludePatterns = new ArrayList<>();

}
