package org.springlink.core.boot.tenant;

/**
 * 租户id生成器
 *
 * @author GL
 */
public interface TenantId {

	/**
	 * 生成自定义租户id
	 *
	 * @return string
	 */
	String generate();

}
