package org.springlink.core.tool.convert;

import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.lang.Nullable;
import org.springframework.util.StringValueResolver;

/**
 * 类型 转换 服务，添加了 IEnum 转换
 *
 */
public class LinkConversionService extends ApplicationConversionService {
	@Nullable
	private static volatile LinkConversionService SHARED_INSTANCE;

	public LinkConversionService() {
		this(null);
	}

	public LinkConversionService(@Nullable StringValueResolver embeddedValueResolver) {
		super(embeddedValueResolver);
		super.addConverter(new EnumToStringConverter());
		super.addConverter(new StringToEnumConverter());
	}

	/**
	 * Return a shared default application {@code ConversionService} instance, lazily
	 * building it once needed.
	 * <p>
	 * Note: This method actually returns an {@link LinkConversionService}
	 * instance. However, the {@code ConversionService} signature has been preserved for
	 * binary compatibility.
	 * @return the shared {@code LinkConversionService} instance (never{@code null})
	 */
	public static GenericConversionService getInstance() {
		LinkConversionService sharedInstance = LinkConversionService.SHARED_INSTANCE;
		if (sharedInstance == null) {
			synchronized (LinkConversionService.class) {
				sharedInstance = LinkConversionService.SHARED_INSTANCE;
				if (sharedInstance == null) {
					sharedInstance = new LinkConversionService();
					LinkConversionService.SHARED_INSTANCE = sharedInstance;
				}
			}
		}
		return sharedInstance;
	}

}
