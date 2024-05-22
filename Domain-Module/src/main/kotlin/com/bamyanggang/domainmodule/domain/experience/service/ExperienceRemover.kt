package com.bamyanggang.domainmodule.domain.experience.service

import com.bamyanggang.domainmodule.domain.experience.repository.ExperienceRepository
import java.util.*

class ExperienceRemover(
    private val experienceRepository: ExperienceRepository,
) {
    fun remove(experienceId: UUID) {
        experienceRepository.deleteByExperienceId(experienceId)
    }
}
