package com.bamyanggang.domainmodule.domain.experience.service

import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceContent
import com.bamyanggang.domainmodule.domain.experience.repository.ExperienceContentRepository
import org.springframework.stereotype.Service

@Service
class ExperienceContentRemover(
    private val experienceContentRepository: ExperienceContentRepository
) {
    fun removeAllByIds(experienceContents: List<ExperienceContent>) {
        experienceContentRepository.deleteAllByIds(experienceContents)
    }
}
