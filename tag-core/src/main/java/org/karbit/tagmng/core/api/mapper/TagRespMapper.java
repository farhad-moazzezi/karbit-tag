package org.karbit.tagmng.core.api.mapper;

import java.util.ArrayList;
import java.util.List;

import org.karbit.tagmng.common.dto.common.TagInfo;
import org.karbit.tagmng.common.dto.response.FoundTagResp;
import org.karbit.tagmng.common.dto.response.TagResp;
import org.karbit.tagmng.core.biz.dto.TagModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TagRespMapper {

	@Named("toTagInfo")
	@Mapping(target = "tagId", source = "tagId")
	@Mapping(target = "caption", source = "caption")
	TagInfo toTagInfo(TagModel tag);

	default List<TagInfo> toTagInfo(List<TagModel> tags) {
		List<TagInfo> tagInfos = new ArrayList<>();
		for (TagModel tag : tags) {
			tagInfos.add(toTagInfo(tag));
		}
		return tagInfos;
	}

	default FoundTagResp toFoundTagResp(List<TagModel> tags) {
		return new FoundTagResp(toTagInfo(tags));
	}

	@Mapping(target = "caption", source = "caption")
	TagResp toTagResp(TagModel tagModel);
}
