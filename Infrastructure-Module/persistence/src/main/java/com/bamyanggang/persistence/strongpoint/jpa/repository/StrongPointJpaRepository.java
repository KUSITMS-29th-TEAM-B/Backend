package com.bamyanggang.persistence.strongpoint.jpa.repository;

import com.bamyanggang.persistence.strongpoint.jpa.entity.StrongPointJpaEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StrongPointJpaRepository extends JpaRepository<StrongPointJpaEntity, UUID> {
    List<StrongPointJpaEntity> findAllByUserId(UUID userId);

    @Modifying
    @Query("select spe from StrongPointJpaEntity spe where spe.strongPointId in :strongPoints")
    List<StrongPointJpaEntity> findByIds(@Param("strongPoints") List<UUID> strongPointIds);
}
