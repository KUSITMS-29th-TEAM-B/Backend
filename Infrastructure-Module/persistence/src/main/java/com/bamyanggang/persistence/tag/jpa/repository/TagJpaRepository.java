package com.bamyanggang.persistence.tag.jpa.repository;

import com.bamyanggang.persistence.tag.jpa.entity.TagJpaEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TagJpaRepository extends JpaRepository<TagJpaEntity, UUID> {
    List<TagJpaEntity> findAllByUserIdAndParentTagIdIsNull(UUID userId);
    List<TagJpaEntity> findAllByUserIdAndParentTagId(UUID parentTagId, UUID parentId);
    List<TagJpaEntity> findByUserIdAndNameContaining(UUID userId, String name);
    List<TagJpaEntity> findAllByParentTagId(UUID parentTagId);

    @Query("""
            select t from TagJpaEntity t, ExperienceJpaEntity e
            where e.parentTagId in :parentTagIds
                and e.startedAt between :startYear and :endYear
                and e.parentTagId = t.tagId
                order by e.createdAt desc
            """
    )
    List<TagJpaEntity> findByParentTagIdAndYearAndExperienceCreatedAtDesc(@Param("parentTagIds") List<UUID> parentTagIds,
                                                                          @Param("startYear") LocalDateTime startYear,
                                                                          @Param("endYear") LocalDateTime endYear);
}
