package com.bamyanggang.persistence.experience;

import com.bamyanggang.persistence.experience.jpa.repository.ExperienceJpaRepository;
import com.bamyanggang.persistence.experience.mapper.ExperienceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExperienceRepositoryImpl {
    private final ExperienceJpaRepository experienceJpaRepository;
    private final ExperienceMapper experienceMapper;
}
