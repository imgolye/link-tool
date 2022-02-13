package org.springlink.core.log.event;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springlink.core.launch.properties.LinkProperties;
import org.springlink.core.launch.server.ServerInfo;
import org.springlink.core.log.constant.EventConstant;
import org.springlink.core.log.feign.ILogClient;
import org.springlink.core.log.model.LogError;
import org.springlink.core.log.utils.LogAbstractUtil;

import java.util.Map;

/**
 * 异步监听错误日志事件
 *
 * @author GL
 */
@Slf4j
@AllArgsConstructor
public class ErrorLogListener {

	private final ILogClient logService;
	private final ServerInfo serverInfo;
	private final LinkProperties linkProperties;

	@Async
	@Order
	@EventListener(ErrorLogEvent.class)
	public void saveErrorLog(ErrorLogEvent event) {
		Map<String, Object> source = (Map<String, Object>) event.getSource();
		LogError logError = (LogError) source.get(EventConstant.EVENT_LOG);
		LogAbstractUtil.addOtherInfoToLog(logError, linkProperties, serverInfo);
		logService.saveErrorLog(logError);
	}

}
