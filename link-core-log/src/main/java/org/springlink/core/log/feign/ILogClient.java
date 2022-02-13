package org.springlink.core.log.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springlink.core.launch.constant.AppConstant;
import org.springlink.core.log.model.LogApi;
import org.springlink.core.log.model.LogError;
import org.springlink.core.log.model.LogUsual;
import org.springlink.core.tool.api.R;

/**
 * Feign接口类
 *
 * @author Chill
 */
@FeignClient(
	value = AppConstant.APPLICATION_LOG_NAME,
	fallback = LogClientFallback.class
)
public interface ILogClient {

	String API_PREFIX = "/log";

	/**
	 * 保存错误日志
	 *
	 * @param log 日志实体
	 * @return boolean
	 */
	@PostMapping(API_PREFIX + "/saveUsualLog")
	R<Boolean> saveUsualLog(@RequestBody LogUsual log);

	/**
	 * 保存操作日志
	 *
	 * @param log 日志实体
	 * @return boolean
	 */
	@PostMapping(API_PREFIX + "/saveApiLog")
	R<Boolean> saveApiLog(@RequestBody LogApi log);

	/**
	 * 保存错误日志
	 *
	 * @param log 日志实体
	 * @return boolean
	 */
	@PostMapping(API_PREFIX + "/saveErrorLog")
	R<Boolean> saveErrorLog(@RequestBody LogError log);

}
