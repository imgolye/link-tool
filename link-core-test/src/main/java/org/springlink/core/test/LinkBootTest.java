package org.springlink.core.test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 简化 测试
 *
 * @author GL
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootTest
@ExtendWith(org.springlink.core.test.LinkSpringExtension.class)
public @interface LinkBootTest {
	/**
	 * 服务名：appName
	 * @return appName
	 */
	@AliasFor("appName")
	String value() default "link-test";
	/**
	 * 服务名：appName
	 * @return appName
	 */
	@AliasFor("value")
	String appName() default "link-test";
	/**
	 * profile
	 * @return profile
	 */
	String profile() default "dev";
	/**
	 * 启用 ServiceLoader 加载 launcherService
	 * @return 是否启用
	 */
	boolean enableLoader() default false;
}
