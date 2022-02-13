package org.springlink.core.log.publisher;


import org.springlink.core.log.constant.EventConstant;
import org.springlink.core.log.event.UsualLogEvent;
import org.springlink.core.log.model.LogUsual;
import org.springlink.core.log.utils.LogAbstractUtil;
import org.springlink.core.tool.utils.SpringUtil;
import org.springlink.core.tool.utils.WebUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * LINK日志信息事件发送
 *
 * @author GL
 */
public class UsualLogPublisher {

	public static void publishEvent(String level, String id, String data) {
		HttpServletRequest request = WebUtil.getRequest();
		LogUsual logUsual = new LogUsual();
		logUsual.setLogLevel(level);
		logUsual.setLogId(id);
		logUsual.setLogData(data);

		LogAbstractUtil.addRequestInfoToLog(request, logUsual);
		Map<String, Object> event = new HashMap<>(16);
		event.put(EventConstant.EVENT_LOG, logUsual);
		event.put(EventConstant.EVENT_REQUEST, request);
		SpringUtil.publishEvent(new UsualLogEvent(event));
	}

}
