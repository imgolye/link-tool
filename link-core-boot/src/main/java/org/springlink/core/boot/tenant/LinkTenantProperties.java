package org.springlink.core.boot.tenant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 多租户配置
 *
 * @author GL
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "link.tenant")
public class LinkTenantProperties {

	/**
	 * 多租户字段名称
	 */
	private String column = "tenant_id";

	/**
	 * 多租户数据表
	 */
	private List<String> tables = new ArrayList<>();

	/**
	 * 多租户系统数据表
	 */
	private List<String> linkTables = Arrays.asList("link_notice", "link_post", "link_log_api", "link_log_error", "link_log_usual");
}
