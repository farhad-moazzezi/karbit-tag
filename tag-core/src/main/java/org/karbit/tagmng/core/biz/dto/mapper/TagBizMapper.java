package org.karbit.tagmng.core.biz.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.karbit.tagmng.common.dto.request.TagCreationReq;
import org.karbit.tagmng.core.biz.dto.TagCreationModel;
import org.karbit.tagmng.core.biz.dto.TagModel;
import org.karbit.tagmng.core.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagBizMapper {

	TagCreationModel toTagCreationModel(TagCreationReq tagCreationReq);

	@Mapping(target = "tagId", source = "uniqueId")
	@Mapping(target = "caption", source = "caption")
	TagModel toTagModel(Tag tag);

	default List<TagModel> toTagModel(List<Tag> tags) {
		return tags.stream().map(this::toTagModel).collect(Collectors.toList());
	}
}
