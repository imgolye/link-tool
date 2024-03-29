package org.springlink.core.secure.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * secure放行额外配置
 */
@Data
@ConfigurationProperties("link.secure")
public class LinkSecureProperties {

	private final List<ClientSecure> client = new ArrayList<>();

	private final List<String> skipUrl = new ArrayList<>();

}
