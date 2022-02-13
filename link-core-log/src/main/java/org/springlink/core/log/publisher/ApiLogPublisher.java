package org.springlink.core.log.publisher;



import org.springlink.core.log.annotation.ApiLog;
import org.springlink.core.log.constant.EventConstant;
import org.springlink.core.log.event.ApiLogEvent;
import org.springlink.core.log.model.LogApi;
import org.springlink.core.log.utils.LogAbstractUtil;
import org.springlink.core.tool.constant.LinkConstant;
import org.springlink.core.tool.utils.SpringUtil;
import org.springlink.core.tool.utils.WebUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * API日志信息事件发送
 *
 * @author GL
 */
public class ApiLogPublisher {

	public static void publishEvent(String methodName, String methodClass, ApiLog apiLog, long time) {
		HttpServletRequest request = WebUtil.getRequest();
		LogApi logApi = new LogApi();
		logApi.setType(LinkConstant.LOG_NORMAL_TYPE);
		logApi.setTitle(apiLog.value());
		logApi.setTime(String.valueOf(time));
		logApi.setMethodClass(methodClass);
		logApi.setMethodName(methodName);

		LogAbstractUtil.addRequestInfoToLog(request, logApi);
		Map<String, Object> event = new HashMap<>(16);
		event.put(EventConstant.EVENT_LOG, logApi);
		SpringUtil.publishEvent(new ApiLogEvent(event));
	}

}
