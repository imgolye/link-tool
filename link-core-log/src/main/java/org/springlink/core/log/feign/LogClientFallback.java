package org.springlink.core.log.feign;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springlink.core.log.model.LogApi;
import org.springlink.core.log.model.LogError;
import org.springlink.core.log.model.LogUsual;
import org.springlink.core.tool.api.R;

/**
 * 日志fallback
 *
 * @author jiang
 */
@Slf4j
@Component
public class LogClientFallback implements ILogClient {

	@Override
	public R<Boolean> saveUsualLog(LogUsual log) {
		return R.fail("usual log send fail");
	}

	@Override
	public R<Boolean> saveApiLog(LogApi log) {
		return R.fail("api log send fail");
	}

	@Override
	public R<Boolean> saveErrorLog(LogError log) {
		return R.fail("error log send fail");
	}
}
