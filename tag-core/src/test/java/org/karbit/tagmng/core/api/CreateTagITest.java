package org.karbit.tagmng.core.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.karbit.skeleton.base.result.dto.BaseRequest;
import org.karbit.skeleton.base.result.dto.ResultSummary;
import org.karbit.tagmng.common.ResultStatus;
import org.karbit.tagmng.common.dto.request.TagCreationReq;
import org.karbit.tagmng.common.dto.response.TagCreationResp;
import org.karbit.tagmng.core.TestUtils;
import org.karbit.tagmng.core.dao.TagDao;
import org.karbit.tagmng.core.model.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class CreateTagITest extends TagManagerITest {
	private static final String SERVICE_URL = "/tag/create";

	private static final String TAG_CAPTION = "TAG CAPTION";

	private static final String TAG_CATEGORY = "TAG CATEGORY";

	@Autowired
	private TagDao tagDao;

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
	}

	protected void removeAllTag() {
		tagDao.deleteAll();
	}

	@Override
	public ResponseEntity<TagCreationResp> sendRequest(HttpHeaders headers, BaseRequest request) {
		return restTemplate.exchange(getTestUrl(), HttpMethod.POST,
				new HttpEntity<>(request, headers), TagCreationResp.class);
	}

	@Test
	void createTag_success() {
		TagCreationReq tagCreationReq = new TagCreationReq();
		tagCreationReq.setCaption(TAG_CAPTION);
		tagCreationReq.setCategory(TAG_CATEGORY);
		ResponseEntity<TagCreationResp> response = sendRequest(testUtils.getDefaultHeader(), tagCreationReq);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();

		assertThat(tagDao.findAll().size()).isEqualTo(1);
		assertThat(tagDao.findByCaption(TAG_CAPTION)).isNotNull();
		assertThat(tagDao.findByCaptionLikeAndStatusOrderByCaption(TAG_CAPTION, Status.ACTIVE, PageRequest.of(0, 10))).isNotNull();
		assertThat(tagDao.findByCaptionLikeAndStatusOrderByCaption(TAG_CAPTION, Status.ACTIVE, PageRequest.of(0, 10)).size()).isEqualTo(1);
	}

	@ParameterizedTest
	@MethodSource(TestUtils.EMPTY_STRING_VALUES)
	void prevent_to_create_tag_with_empty_caption(String caption) {
		TagCreationReq tagCreationReq = new TagCreationReq();
		tagCreationReq.setCaption(caption);
		tagCreationReq.setCategory(TAG_CATEGORY);
		ResponseEntity<TagCreationResp> response = sendRequest(testUtils.getDefaultHeader(), tagCreationReq);

		assertThat(response).isNotNull();
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
		assertThat(response.getBody().getResult()).isEqualTo(ResultSummary.of(ResultStatus.EX_EMPTY_TAG_CAPTION));
		assertThat(tagDao.findAll().size()).isZero();
	}

	@ParameterizedTest
	@MethodSource(TestUtils.EMPTY_STRING_VALUES)
	void prevent_to_create_tag_with_empty_category(String category) {
		TagCreationReq tagCreationReq = new TagCreationReq();
		tagCreationReq.setCaption(TAG_CAPTION);
		tagCreationReq.setCategory(category);
		ResponseEntity<TagCreationResp> response = sendRequest(testUtils.getDefaultHeader(), tagCreationReq);

		assertThat(response).isNotNull();
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
		assertThat(response.getBody().getResult()).isEqualTo(ResultSummary.of(ResultStatus.EX_EMPTY_TAG_CATEGORY));
		assertThat(tagDao.findAll().size()).isZero();
	}
}
