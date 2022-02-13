package org.springlink.core.cloud.header;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springlink.core.cloud.props.LinkFeignHeadersProperties;

import java.util.concurrent.Callable;

/**
 * HttpHeaders hystrix Callable
 *
 * @param <V> 泛型标记
 */
public class LinkHttpHeadersCallable<V> implements Callable<V> {
	private final Callable<V> delegate;
	@Nullable
	private HttpHeaders httpHeaders;

	public LinkHttpHeadersCallable(Callable<V> delegate,
								   @Nullable LinkFeignAccountGetter accountGetter,
								   LinkFeignHeadersProperties properties) {
		this.delegate = delegate;
		this.httpHeaders = LinkHttpHeadersContextHolder.toHeaders(accountGetter, properties);
	}

	@Override
	public V call() throws Exception {
		if (httpHeaders == null) {
			return delegate.call();
		}
		try {
			LinkHttpHeadersContextHolder.set(httpHeaders);
			return delegate.call();
		} finally {
			LinkHttpHeadersContextHolder.remove();
			httpHeaders.clear();
			httpHeaders = null;
		}
	}
}
