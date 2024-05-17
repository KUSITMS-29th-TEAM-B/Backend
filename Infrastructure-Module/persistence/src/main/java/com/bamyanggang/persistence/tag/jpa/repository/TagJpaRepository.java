package com.bamyanggang.persistence.tag.jpa.repository;

import com.bamyanggang.persistence.tag.jpa.entity.TagJpaEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagJpaRepository extends JpaRepository<TagJpaEntity, UUID> {
    List<TagJpaEntity> findAllByUserIdAndParentTagIdIsNull(UUID userId);

    List<TagJpaEntity> findAllByUserIdAndParentTagId(UUID parentTagId, UUID parentId);
}
