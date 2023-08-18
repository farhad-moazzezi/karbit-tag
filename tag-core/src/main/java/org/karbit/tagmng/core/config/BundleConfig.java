package org.karbit.tagmng.core.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class BundleConfig {

	@Bean("bundleHolder")
	public MessageSource createMessageSourceBean() {
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setDefaultEncoding("UTF-8");
		resourceBundleMessageSource.setBasenames("bundle/notification", "bundle/result");
		return resourceBundleMessageSource;
	}
}
