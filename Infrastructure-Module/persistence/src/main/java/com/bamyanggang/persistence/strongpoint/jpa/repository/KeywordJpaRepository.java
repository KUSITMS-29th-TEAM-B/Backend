package com.bamyanggang.persistence.strongpoint.jpa.repository;

import com.bamyanggang.persistence.strongpoint.jpa.entity.KeywordJpaEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KeywordJpaRepository extends JpaRepository<KeywordJpaEntity, UUID> {
    @Query("select k from KeywordJpaEntity k where k.keywordId in :strongPointIds")
    List<KeywordJpaEntity> findByKeywordIds(@Param("strongPointIds") List<UUID> strongPointIds);
}
