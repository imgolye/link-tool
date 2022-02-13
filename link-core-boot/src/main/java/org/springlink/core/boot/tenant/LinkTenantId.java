package org.springlink.core.boot.tenant;


import org.springlink.core.tool.utils.RandomType;
import org.springlink.core.tool.utils.StringUtil;

/**
 * link租户id生成器
 *
 * @author Chill
 */
public class LinkTenantId implements TenantId {
	@Override
	public String generate() {
		return StringUtil.random(6, RandomType.INT);
	}
}
