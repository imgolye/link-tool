package org.springlink.core.mp.plugins;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springlink.core.mp.intercept.QueryInterceptor;
import org.springlink.core.tool.utils.ObjectUtil;

/**
 * 查询拦截器执行器
 *
 * <p>
 * 目的：抽取此方法是为了后期方便同步更新 {@link LinkPaginationInterceptor}
 * </p>
 *
 * @author L.cm
 */
@SuppressWarnings({"rawtypes"})
public class QueryInterceptorExecutor {

	/**
	 * 执行查询拦截器
	 */
	static void exec(QueryInterceptor[] interceptors, Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws Throwable {
		if (ObjectUtil.isEmpty(interceptors)) {
			return;
		}
		for (QueryInterceptor interceptor : interceptors) {
			interceptor.intercept(executor, ms, parameter, rowBounds, resultHandler, boundSql);
		}
	}

}
