package org.springlink.core.cloud.header;



import org.springlink.core.secure.LinkUser;
import org.springlink.core.secure.utils.SecureUtil;
import org.springlink.core.tool.utils.Charsets;
import org.springlink.core.tool.utils.UrlUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户信息获取器
 *
 * @author Chill
 */
public class LinkAccountGetter implements LinkFeignAccountGetter {

	@Override
	public String get(HttpServletRequest request) {
		LinkUser account = SecureUtil.getUser();
		if (account == null) {
			return null;
		}
		// 增加用户头, 123[admin]
		String xAccount = String.format("%s[%s]", account.getUserId(), account.getUserName());
		return UrlUtil.encodeURL(xAccount, Charsets.UTF_8);
	}

}
