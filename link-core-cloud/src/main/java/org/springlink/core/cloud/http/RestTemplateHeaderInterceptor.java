package org.springlink.core.cloud.http;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.Nullable;
import org.springlink.core.cloud.header.LinkFeignAccountGetter;
import org.springlink.core.cloud.header.LinkHttpHeadersContextHolder;
import org.springlink.core.cloud.props.LinkFeignHeadersProperties;

import java.io.IOException;

/**
 * RestTemplateHeaderInterceptor 传递Request header
 */
@AllArgsConstructor
public class RestTemplateHeaderInterceptor implements ClientHttpRequestInterceptor {
	@Nullable
	private final LinkFeignAccountGetter accountGetter;
	private final LinkFeignHeadersProperties properties;

	@Override
	public ClientHttpResponse intercept(
		HttpRequest request, byte[] bytes,
		ClientHttpRequestExecution execution) throws IOException {
		HttpHeaders headers = LinkHttpHeadersContextHolder.get();
		// 考虑2中情况 1. RestTemplate 不是用 hystrix 2. 使用 hystrix
		if (headers == null) {
			headers = LinkHttpHeadersContextHolder.toHeaders(accountGetter, properties);
		}
		if (headers != null && !headers.isEmpty()) {
			HttpHeaders httpHeaders = request.getHeaders();
			headers.forEach((key, values) -> {
				values.forEach(value -> httpHeaders.add(key, value));
			});
		}
		return execution.execute(request, bytes);
	}
}
