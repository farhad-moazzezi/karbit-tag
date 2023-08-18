package org.karbit.tagmng.core.config;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "tag")
public class TagProp {

	private Search search;

	@Getter
	@Setter
	public static class Caption {
		private Integer minLength;

		private Integer maxLength;
	}

	@Getter
	@Setter
	public static class Search {
		private int pageSize;
	}
}
