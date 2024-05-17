package com.bamyanggang.persistence.experience;

import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceContent;
import com.bamyanggang.domainmodule.domain.experience.repository.ExperienceContentRepository;
import com.bamyanggang.persistence.experience.jpa.entity.ExperienceContentJpaEntity;
import com.bamyanggang.persistence.experience.jpa.repository.ExperienceContentJpaRepository;
import com.bamyanggang.persistence.experience.mapper.ExperienceContentMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExperienceContentImpl implements ExperienceContentRepository {
    private final ExperienceContentJpaRepository experienceContentJpaRepository;
    private final ExperienceContentMapper experienceContentMapper;

    @Override
    public UUID save(ExperienceContent experienceContent) {
        ExperienceContentJpaEntity experienceContentJpaEntity = experienceContentMapper.toJpaEntity(experienceContent);
        experienceContentJpaRepository.save(experienceContentJpaEntity);

        return experienceContentJpaEntity.getExperienceContentId();
    }
}
