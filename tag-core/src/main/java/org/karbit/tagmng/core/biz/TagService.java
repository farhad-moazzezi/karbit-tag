package org.karbit.tagmng.core.biz;

import java.util.List;
import java.util.Set;

import org.karbit.tagmng.core.biz.dto.TagCreationModel;
import org.karbit.tagmng.core.biz.dto.TagModel;
import org.karbit.tagmng.core.model.Tag;

public interface TagService {
	Tag createTag(TagCreationModel tagCreationModel);

	void removeTag(String tagId);

	TagModel findTag(String tagId);

	List<TagModel> searchTag(String value, Integer pageNumber, Integer pageSize);

	List<TagModel> findOrInsert(Set<String> values);
}
