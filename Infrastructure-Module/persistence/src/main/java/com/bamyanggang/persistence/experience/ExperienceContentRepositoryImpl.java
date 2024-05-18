package com.bamyanggang.persistence.experience;

import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceContent;
import com.bamyanggang.domainmodule.domain.experience.repository.ExperienceContentRepository;
import com.bamyanggang.persistence.experience.jpa.entity.ExperienceContentJpaEntity;
import com.bamyanggang.persistence.experience.jpa.repository.ExperienceContentJpaRepository;
import com.bamyanggang.persistence.experience.mapper.ExperienceMapper;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExperienceContentRepositoryImpl implements ExperienceContentRepository {
    private final ExperienceContentJpaRepository experienceContentJpaRepository;
    private final ExperienceMapper experienceMapper;
    @Override
    public void save(ExperienceContent experienceContent) {
        ExperienceContentJpaEntity experienceContentJpaEntity = experienceMapper.toExperienceContentJpaEntity(
                experienceContent);

        experienceContentJpaRepository.save(experienceContentJpaEntity);
    }

    @Override
    public List<ExperienceContent> findByExperienceId(UUID experienceId) {
        return experienceContentJpaRepository.findByExperienceId(experienceId).stream()
                .map(experienceMapper::toExperienceContentDomainEntity).toList();
    }

    @Override
    public void deleteAllByIds(List<ExperienceContent> experienceContents) {
        List<ExperienceContentJpaEntity> experienceContentJpaEntities = experienceContents.stream()
                .map(experienceMapper::toExperienceContentJpaEntity).toList();

        experienceContentJpaRepository.deleteAllByQuery(experienceContentJpaEntities);
    }
}
