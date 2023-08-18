package org.karbit.tagmng.core.api;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.karbit.skeleton.base.result.dto.BaseResponse;
import org.karbit.tagmng.common.dto.request.FinsertReq;
import org.karbit.tagmng.common.dto.request.SearchTagReq;
import org.karbit.tagmng.common.dto.request.TagCreationReq;
import org.karbit.tagmng.common.dto.response.FoundTagResp;
import org.karbit.tagmng.common.dto.response.TagCreationResp;
import org.karbit.tagmng.common.dto.response.TagResp;
import org.karbit.tagmng.core.api.mapper.TagRespMapper;
import org.karbit.tagmng.core.biz.TagService;
import org.karbit.tagmng.core.biz.dto.TagModel;
import org.karbit.tagmng.core.biz.dto.mapper.TagBizMapper;
import org.karbit.tagmng.core.config.TagProp;
import org.karbit.tagmng.core.util.PaginationUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TagManager {

	private final TagService tagService;

	private final TagBizMapper tagBizMapper;

	private final TagRespMapper tagRespMapper;

	private final TagProp tagProp;

	@DeleteMapping(value = "/{tagId}/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse> removeTag(@PathVariable String tagId) {
		tagService.removeTag(tagId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FoundTagResp> searchTag(@RequestBody SearchTagReq searchTagReq) {
		List<TagModel> tags = tagService.searchTag(
				searchTagReq.getValue(),
				PaginationUtil.convert(searchTagReq.getPage()),
				tagProp.getSearch().getPageSize()
		);
		return ResponseEntity.ok(tagRespMapper.toFoundTagResp(tags));
	}

	@PostMapping(value = "/finsert", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FoundTagResp> finsert(@RequestBody FinsertReq finsertReq) {
		List<TagModel> tagsTagModels = tagService.findOrInsert(finsertReq.getValues());
		return ResponseEntity.ok(tagRespMapper.toFoundTagResp(tagsTagModels));
	}

	@GetMapping(value = "/{tagId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TagResp> getTag(@PathVariable String tagId) {
		return ResponseEntity.ok(tagRespMapper.toTagResp(tagService.findTag(tagId)));
	}
}
