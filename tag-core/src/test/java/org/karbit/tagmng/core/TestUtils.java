package org.karbit.tagmng.core;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class TestUtils {

	public static final String EMPTY_STRING_VALUES = "org.karbit.tagmng.core.TestUtils#emptyStringValue";

	public static List<String> emptyStringValue() {
		return Arrays.asList("", " ", null);
	}

	public HttpHeaders getDefaultHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
}
