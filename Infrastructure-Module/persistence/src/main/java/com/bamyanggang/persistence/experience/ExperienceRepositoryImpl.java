package com.bamyanggang.persistence.experience;

import com.bamyanggang.domainmodule.domain.experience.aggregate.Experience;
import com.bamyanggang.domainmodule.domain.experience.repository.ExperienceRepository;
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
    public UUID save(Experience experience) {
        ExperienceJpaEntity experienceJpaEntity = experienceMapper.toJpaEntity(experience);
        experienceJpaRepository.save(experienceJpaEntity);

        return experienceJpaEntity.getExperienceId();
    }
}
