package com.bamyanggang.domainmodule.domain.experience.service

import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceContent
import com.bamyanggang.domainmodule.domain.experience.repository.ExperienceContentRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExperienceContentReader(
    private val experienceRepository: ExperienceContentRepository
) {
    fun readByExperienceId(experienceId: UUID): List<ExperienceContent> {
        return experienceRepository.findByExperienceId(experienceId)
    }
}
