package com.bamyanggang.domainmodule.domain.experience.service

import com.bamyanggang.domainmodule.domain.experience.repository.ExperienceRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExperienceRemover(
    private val experienceRepository: ExperienceRepository,
) {
    fun remove(experienceId: UUID) {
        experienceRepository.deleteByExperienceId(experienceId)
    }
}
