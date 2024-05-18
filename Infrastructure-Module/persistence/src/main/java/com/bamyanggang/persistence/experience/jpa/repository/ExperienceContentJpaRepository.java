package com.bamyanggang.persistence.experience.jpa.repository;

import com.bamyanggang.persistence.experience.jpa.entity.ExperienceContentJpaEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExperienceContentJpaRepository extends JpaRepository<ExperienceContentJpaEntity, UUID> {
    List<ExperienceContentJpaEntity> findByExperienceId(UUID experienceId);

    @Modifying
    @Query("delete from ExperienceContentJpaEntity ec where ec in :experienceContents")
    void deleteAllByQuery(@Param("experienceContents") List<ExperienceContentJpaEntity> experienceContents);
}
