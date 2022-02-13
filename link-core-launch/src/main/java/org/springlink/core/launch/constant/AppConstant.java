package org.springlink.core.launch.constant;

/**
 * 系统常量
 */
public interface AppConstant {

	/**
	 * 应用版本
	 */
	String APPLICATION_VERSION = "1.0.0";

	/**
	 * 基础包
	 */
	String BASE_PACKAGES = "org.springlink";

	/**
	 * 应用名前缀
	 */
	String APPLICATION_NAME_PREFIX = "link-";
	/**
	 * 网关模块名称
	 */
	String APPLICATION_GATEWAY_NAME = APPLICATION_NAME_PREFIX + "gateway";
	/**
	 * 授权模块名称
	 */
	String APPLICATION_AUTH_NAME = APPLICATION_NAME_PREFIX + "auth";
	/**
	 * 监控模块名称
	 */
	String APPLICATION_ADMIN_NAME = APPLICATION_NAME_PREFIX + "admin";
	/**
	 * 首页模块名称
	 */
	String APPLICATION_DESK_NAME = APPLICATION_NAME_PREFIX + "desk";


	/**
	 * 系统模块名称
	 */
	String APPLICATION_SYSTEM_NAME = APPLICATION_NAME_PREFIX + "system";
	/**
	 * 用户模块名称
	 */
	String APPLICATION_USER_NAME = APPLICATION_NAME_PREFIX + "user";
	/**
	 * 日志模块名称
	 */
	String APPLICATION_LOG_NAME = APPLICATION_NAME_PREFIX + "log";
	/**
	 * 开发模块名称
	 */
	String APPLICATION_DEVELOP_NAME = APPLICATION_NAME_PREFIX + "develop";
	/**
	 * 资源模块名称
	 */
	String APPLICATION_RESOURCE_NAME = APPLICATION_NAME_PREFIX + "resource";
	/**
	 * 链路追踪模块名称
	 */
	String APPLICATION_ZIPKIN_NAME = APPLICATION_NAME_PREFIX + "zipkin";
	/**
	 * 报表系统名称
	 */
	String APPLICATION_REPORT_NAME = APPLICATION_NAME_PREFIX + "report";
	/**
	 * 测试模块名称
	 */
	String APPLICATION_TEST_NAME = APPLICATION_NAME_PREFIX + "test";

	/**
	 * 开发环境
	 */
	String DEV_CODE = "dev";
	/**
	 * 生产环境
	 */
	String PROD_CODE = "prod";
	/**
	 * 测试环境
	 */
	String TEST_CODE = "test";

	/**
	 * 代码部署于 linux 上，工作默认为 mac 和 Windows
	 */
	String OS_NAME_LINUX = "LINUX";

}
