package org.karbit.tagmng.core.api;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.karbit.skeleton.base.result.dto.BaseRequest;
import org.karbit.skeleton.base.result.dto.ResultSummary;
import org.karbit.tagmng.common.ResultStatus;
import org.karbit.tagmng.common.dto.common.TagInfo;
import org.karbit.tagmng.common.dto.request.FinsertReq;
import org.karbit.tagmng.common.dto.response.FoundTagResp;
import org.karbit.tagmng.core.TestUtils;
import org.karbit.tagmng.core.biz.TagService;
import org.karbit.tagmng.core.biz.dto.TagCreationModel;
import org.karbit.tagmng.core.dao.TagDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class FinsertITest extends TagManagerITest {
	private static final String SERVICE_URL = "/tag/finsert";

	private static final String EXIST_TAG_CAPTION = "EXIST TAG CAPTION";

	private static final String NOT_EXIST_TAG_CAPTION = "NOT EXIST TAG CAPTION";

	private static final TagCreationModel EXIST_TAG = new TagCreationModel(EXIST_TAG_CAPTION);

	@Autowired
	private TagDao tagDao;

	@Autowired
	private TagService tagService;


	@Autowired
	protected TestUtils testUtils;

	@Override
	protected String getServiceUrl() {
		return SERVICE_URL;
	}

	@Override
	@BeforeEach
	protected void beforeTest() {
		removeAllTag();
		createTag();
	}

	private void createTag() {
		tagService.createTag(EXIST_TAG);
	}

	protected void removeAllTag() {
		tagDao.deleteAll();
	}

	@Override
	public ResponseEntity<FoundTagResp> sendRequest(HttpHeaders headers, BaseRequest request) {
		return restTemplate.exchange(getTestUrl(), HttpMethod.POST,
				new HttpEntity<>(request, headers), FoundTagResp.class);
	}

	@Test
	void finsert_success() {
		FinsertReq finsertReq = new FinsertReq(new HashSet<>(Arrays.asList(EXIST_TAG_CAPTION, NOT_EXIST_TAG_CAPTION)));
		ResponseEntity<FoundTagResp> response = sendRequest(testUtils.getDefaultHeader(), finsertReq);

		assertThat(response).isNotNull();
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getTags()).isNotNull();
		assertThat(response.getBody().getTags().size()).isEqualTo(2);
		for (TagInfo tagInfo : response.getBody().getTags()) {
			assertThat(tagInfo).isNotNull();
			assertThat(tagInfo.getTagId()).isNotBlank();
			assertThat(tagInfo.getCaption()).isNotBlank();
		}
	}
}
