package org.karbit.tagmng.core.model.mapper;

import org.karbit.tagmng.core.biz.dto.TagCreationModel;
import org.karbit.tagmng.core.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagMapper {

	@Mapping(target = "caption", source = "caption")
	Tag toTag(TagCreationModel tagCreationModel);
}
