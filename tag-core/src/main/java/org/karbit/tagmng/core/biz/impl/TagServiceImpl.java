package org.karbit.tagmng.core.biz.impl;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.karbit.tagmng.common.exception.EmptyTagCaptionException;
import org.karbit.tagmng.common.exception.EmptyTagCategoryException;
import org.karbit.tagmng.common.exception.TagNotFoundException;
import org.karbit.tagmng.core.biz.TagService;
import org.karbit.tagmng.core.biz.dto.TagCreationModel;
import org.karbit.tagmng.core.biz.dto.TagModel;
import org.karbit.tagmng.core.biz.dto.mapper.TagBizMapper;
import org.karbit.tagmng.core.dao.TagDao;
import org.karbit.tagmng.core.model.Status;
import org.karbit.tagmng.core.model.Tag;
import org.karbit.tagmng.core.model.mapper.TagMapper;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
class TagServiceImpl implements TagService {

	private final TagDao tagDao;

	private final TagMapper tagMapper;

	private final TagBizMapper tagBizMapper;

	@Override
	public Tag createTag(TagCreationModel model) {
		log.info("going to create tag -> {}", model);
		checkBeforeSaveTag(model);
		Tag tag = findTagByCaption(model.getCaption());
		if (Objects.isNull(tag)) {
			tag = tagMapper.toTag(model);
		}
		if (Boolean.FALSE.equals(Objects.equals(Status.ACTIVE, tag.getStatus()))) {
			tag.setStatus(Status.ACTIVE);
		}
		return saveTag(tag);
	}

	@Override
	public void removeTag(String tagId) {
		log.info("going to remove tag -> tagId : {}", tagId);
		Tag tag = findTagById(tagId);
		tag.setStatus(Status.REMOVED);
		saveTag(tag);
	}

	private void checkBeforeSaveTag(TagCreationModel model) {
		log.info("going to check input value before save tag -> {}", model);

		if (Boolean.FALSE.equals(StringUtils.hasText(model.getCaption()))) {
			throw new EmptyTagCaptionException("tag caption is empty!");
		}
	}

	private Tag saveTag(Tag tag) {
		log.info("going to save tag -> {}", tag);
		return tagDao.save(tag);
	}

	public Tag findTagById(String tagId) {
		log.info("going to find tag -> tagId : {}", tagId);
		return tagDao.findByUniqueId(tagId).orElseThrow(() -> {
			throw new TagNotFoundException("tag not found!");
		});
	}

	@Override
	public TagModel findTag(String tagId) {
		return tagBizMapper.toTagModel(findTagById(tagId));
	}

	private Tag findTagByCaption(String caption) {
		log.info("going to find tag -> caption : {}", caption);
		return tagDao.findByCaption(caption.trim());
	}

	@Override
	public List<TagModel> searchTag(String value, Integer pageNumber, Integer pageSize) {
		log.info("going to find tag -> caption : {}", value);
		List<Tag> tags = tagDao.findByCaptionLikeAndStatusOrderByCaption(value, Status.ACTIVE, PageRequest.of(pageNumber, pageSize));
		if (Boolean.FALSE.equals(hasTag(tags, value))) {
			tags.add(new Tag(value, Status.ACTIVE));
		}
		return tagBizMapper.toTagModel(tags);
	}

	private boolean hasTag(List<Tag> tags, String value) {
		return tags.stream().anyMatch(tag -> tag.getCaption().equalsIgnoreCase(value));
	}

	@Override
	public List<TagModel> findOrInsert(Set<String> values) {
		log.info("going to find or insert tag -> values : {}", values);
		List<Tag> foundTags = tagDao.findByCaptionIn(values);
		log.info("found tags -> {} ", foundTags);
		foundTags.stream().map(Tag::getCaption).toList().forEach(values::remove);
		values.forEach(value -> foundTags.add(createTag(new TagCreationModel(value))));
		return tagBizMapper.toTagModel(foundTags);
	}
}
