package org.karbit.tagmng.core.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.karbit.tagmng.core.model.Status;
import org.karbit.tagmng.core.model.Tag;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagDao extends MongoRepository<Tag, String> {

	Optional<Tag> findByUniqueId(String tagId);

	Tag findByCaption(String caption);

	List<Tag> findByCaptionLikeAndStatusOrderByCaption(String caption, Status status, Pageable pageable);

	List<Tag> findByCaptionIn(Set<String> values);
}
