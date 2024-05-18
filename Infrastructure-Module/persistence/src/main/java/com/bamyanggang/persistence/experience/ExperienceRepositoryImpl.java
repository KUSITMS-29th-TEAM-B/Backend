package com.bamyanggang.persistence.experience;

import com.bamyanggang.domainmodule.domain.experience.aggregate.Experience;
import com.bamyanggang.domainmodule.domain.experience.repository.ExperienceRepository;
import com.bamyanggang.persistence.common.exception.PersistenceException.NotFound;
import com.bamyanggang.persistence.experience.jpa.entity.ExperienceJpaEntity;
import com.bamyanggang.persistence.experience.jpa.repository.ExperienceJpaRepository;
import com.bamyanggang.persistence.experience.mapper.ExperienceMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExperienceRepositoryImpl implements ExperienceRepository {
    private final ExperienceJpaRepository experienceJpaRepository;
    private final ExperienceMapper experienceMapper;

    @Override
    public void save(Experience experience) {
        ExperienceJpaEntity experienceJpaEntity = experienceMapper.toExperienceJpaEntity(experience);
        experienceJpaRepository.save(experienceJpaEntity);
    }

    @Override
    public void deleteByExperienceId(UUID experienceId) {
        experienceJpaRepository.deleteById(experienceId);
    }

    @Override
    public Experience findByExperienceId(UUID experienceId) {
        ExperienceJpaEntity experienceJpaEntity = experienceJpaRepository.findById(experienceId)
                .orElseThrow(NotFound::new);

        return experienceMapper.toExperienceDomainEntity(experienceJpaEntity);
    }
}
