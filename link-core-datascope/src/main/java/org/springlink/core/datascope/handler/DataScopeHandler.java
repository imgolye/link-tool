package org.springlink.core.datascope.handler;


import org.springlink.core.datascope.model.DataScopeModel;
import org.springlink.core.secure.LinkUser;

/**
 * 数据权限规则
 *
 * @author Chill
 */
public interface DataScopeHandler {

	/**
	 * 获取过滤sql
	 *
	 * @param mapperId    数据查询类
	 * @param dataScope   数据权限类
	 * @param linkUser   当前用户信息
	 * @param originalSql 原始Sql
	 * @return sql
	 */
	String sqlCondition(String mapperId, DataScopeModel dataScope, LinkUser linkUser, String originalSql);

}
