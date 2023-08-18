package org.karbit.tagmng.core;

import org.junit.jupiter.api.BeforeEach;
import org.karbit.skeleton.base.result.dto.BaseRequest;
import org.karbit.skeleton.base.result.dto.BaseResponse;
import org.karbit.skeleton.base.result.exception.NotImplementedException;
import org.karbit.tagmng.common.dto.response.BaseTagServiceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = TagManagerBootstrap.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource({ "classpath:application-test.properties" })
public abstract class AbstractIntegrationTest {

	@LocalServerPort
	protected int PORT;

	@Autowired
	protected TestUtils testUtils;

	@Autowired
	protected TestRestTemplate restTemplate;

	protected abstract String getServiceUrl();

	@BeforeEach
	protected abstract void beforeTest();

	public String getTestUrl() {
		return String.format("http://localhost:%s%s", PORT, getServiceUrl());
	}

	public String getTestUrl(String serviceUrl) {
		return String.format("http://localhost:%s%s", PORT, serviceUrl);
	}

	public String getTestUrlWithParam(String param) {
		return String.format("http://localhost:%s%s/%s", PORT, getServiceUrl(), param);
	}

	public String getTestUrlWithParam(String serviceUrl, String param) {
		return String.format("http://localhost:%s%s/%s", PORT, serviceUrl, param);
	}

	public ResponseEntity<? extends BaseTagServiceResponse> sendRequest(HttpHeaders headers, BaseRequest request) {
		throw new NotImplementedException("not implement method!");
	}

	public ResponseEntity<? extends BaseTagServiceResponse> sendRequest(HttpHeaders headers, String value) {
		throw new NotImplementedException("not implement method!");
	}

	public ResponseEntity<? extends BaseTagServiceResponse> sendRequest(HttpHeaders headers, int value) {
		throw new NotImplementedException("not implement method!");
	}
}
