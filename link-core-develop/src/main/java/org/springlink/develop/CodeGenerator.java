package org.springlink.develop;


import org.springlink.develop.support.LinkCodeGenerator;

/**
 * 代码生成器
 *
 * @author GL
 */
public class CodeGenerator {

	/**
	 * 代码生成的模块名
	 */
	public static String CODE_NAME = "应用管理";
	/**
	 * 代码所在服务名
	 */
	public static String SERVICE_NAME = "link-system";
	/**
	 * 代码生成的包名
	 */
	public static String PACKAGE_NAME = "org.springlink.system";
	/**
	 * 前端代码生成所属系统
	 */
	public static String SYSTEM_NAME = "saber";
	/**
	 * 前端代码生成地址
	 */
	public static String PACKAGE_WEB_DIR = "/Users/gaolei/Workspaces/product/Saber";
	/**
	 * 需要去掉的表前缀
	 */
	public static String[] TABLE_PREFIX = {"link_"};
	/**
	 * 需要生成的表名(两者只能取其一)
	 */
	public static String[] INCLUDE_TABLES = {"link_client"};
	/**
	 * 需要排除的表名(两者只能取其一)
	 */
	public static String[] EXCLUDE_TABLES = {};
	/**
	 * 是否包含基础业务字段
	 */
	public static Boolean HAS_SUPER_ENTITY = Boolean.TRUE;
	/**
	 * 基础业务字段
	 */
	public static String[] SUPER_ENTITY_COLUMNS = {"create_time", "create_user", "update_time", "update_user", "status", "is_deleted"};


	/**
	 * RUN THIS
	 */
	public static void run() {
		LinkCodeGenerator generator = new LinkCodeGenerator();
		generator.setCodeName(CODE_NAME);
		generator.setServiceName(SERVICE_NAME);
		generator.setSystemName(SYSTEM_NAME);
		generator.setPackageName(PACKAGE_NAME);
		generator.setPackageWebDir(PACKAGE_WEB_DIR);
		generator.setTablePrefix(TABLE_PREFIX);
		generator.setIncludeTables(INCLUDE_TABLES);
		generator.setExcludeTables(EXCLUDE_TABLES);
		generator.setHasSuperEntity(HAS_SUPER_ENTITY);
		generator.setSuperEntityColumns(SUPER_ENTITY_COLUMNS);
		generator.run();
	}

}
