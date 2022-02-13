package org.springlink.core.cloud.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springlink.core.tool.api.R;
import org.springlink.core.tool.jackson.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Sentinel统一限流策略
 */
public class LinkBlockExceptionHandler implements BlockExceptionHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
		// Return 429 (Too Many Requests) by default.
		response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.getWriter().print(JsonUtil.toJson(R.fail(e.getMessage())));
	}
}
