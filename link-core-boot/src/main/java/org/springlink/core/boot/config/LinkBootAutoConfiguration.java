package org.springlink.core.boot.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springlink.core.launch.properties.LinkProperties;
import org.springlink.core.tool.constant.SystemConstant;

/**
 * 配置类
 *
 * @author GL
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({
	LinkProperties.class
})
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@AllArgsConstructor
public class LinkBootAutoConfiguration {

	private LinkProperties linkProperties;

	/**
	 * 全局变量定义
	 *
	 * @return SystemConstant
	 */
	@Bean
	public SystemConstant fileConst() {
		SystemConstant me = SystemConstant.me();

		//设定开发模式
		me.setDevMode(("dev".equals(linkProperties.getEnv())));

		//设定文件上传远程地址
		me.setDomain(linkProperties.get("upload-domain", "http://localhost:8888"));

		//设定文件上传是否为远程模式
		me.setRemoteMode(linkProperties.getBoolean("remote-mode", true));

		//远程上传地址
		me.setRemotePath(linkProperties.get("remote-path", System.getProperty("user.dir") + "/work/link"));

		//设定文件上传头文件夹
		me.setUploadPath(linkProperties.get("upload-path", "/upload"));

		//设定文件下载头文件夹
		me.setDownloadPath(linkProperties.get("download-path", "/download"));

		//设定上传图片是否压缩
		me.setCompress(linkProperties.getBoolean("compress", false));

		//设定上传图片压缩比例
		me.setCompressScale(linkProperties.getDouble("compress-scale", 2.00));

		//设定上传图片缩放选择:true放大;false缩小
		me.setCompressFlag(linkProperties.getBoolean("compress-flag", false));

		return me;
	}

}
