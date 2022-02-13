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
import org.springlink.core.log.model.LogUsual;
import org.springlink.core.log.utils.LogAbstractUtil;

import java.util.Map;

/**
 * 异步监听日志事件
 *
 * @author GL
 */
@Slf4j
@AllArgsConstructor
public class UsualLogListener {

	private final ILogClient logService;
	private final ServerInfo serverInfo;
	private final LinkProperties linkProperties;

	@Async
	@Order
	@EventListener(UsualLogEvent.class)
	public void saveUsualLog(UsualLogEvent event) {
		Map<String, Object> source = (Map<String, Object>) event.getSource();
		LogUsual logUsual = (LogUsual) source.get(EventConstant.EVENT_LOG);
		LogAbstractUtil.addOtherInfoToLog(logUsual, linkProperties, serverInfo);
		logService.saveUsualLog(logUsual);
	}

}
