package com.bamyanggang.domainmodule.domain.experience.service

import com.bamyanggang.domainmodule.domain.experience.repository.ExperienceRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExperienceRemover(
    private val experienceRepository: ExperienceRepository,
    private val experienceContentReader: ExperienceContentReader,
    private val experienceContentRemover: ExperienceContentRemover
) {
    fun remove(experienceId: UUID) {
        val experienceContents = experienceContentReader.readByExperienceId(experienceId)
        experienceContentRemover.removeAllByIds(experienceContents)

        experienceRepository.deleteByExperienceId(experienceId)
    }
}
