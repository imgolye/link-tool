package org.springlink.core.boot.tenant;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.springlink.core.secure.utils.SecureUtil;
import org.springlink.core.tool.utils.Func;
import org.springlink.core.tool.utils.StringUtil;


/**
 * 租户信息处理器
 *
 * @author GL
 */
@Slf4j
@AllArgsConstructor
public class LinkTenantHandler implements TenantLineHandler {

	private final LinkTenantProperties properties;

	/**
	 * 获取租户ID
	 *
	 * @return 租户ID
	 */
	@Override
	public Expression getTenantId() {
		return new StringValue(Func.toStr(SecureUtil.getTenantId(), TenantConstant.DEFAULT_TENANT_ID));
	}

	/**
	 * 获取租户字段名称
	 *
	 * @return 租户字段名称
	 */
	@Override
	public String getTenantIdColumn() {
		return properties.getColumn();
	}

	/**
	 * 过滤租户表
	 *
	 * @param tableName 表名
	 * @return 是否忽略, true:表示忽略，false:需要解析并拼接多租户条件
	 */
	@Override
	public boolean ignoreTable(String tableName) {
		return !(
			(
				(properties.getTables().size() > 0 && properties.getTables().contains(tableName))
					|| properties.getLinkTables().contains(tableName)
			)
				&& StringUtil.isNotBlank(SecureUtil.getTenantId())
		);
	}
}
