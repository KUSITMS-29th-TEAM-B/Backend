package com.bamyanggang.domainmodule.domain.experience.service

import com.bamyanggang.domainmodule.domain.experience.repository.ExperienceContentRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExperienceContentRemover(
    private val experienceContentRepository: ExperienceContentRepository
) {
    fun removeAllByIds(experienceIds: List<UUID>) {
        experienceContentRepository.deleteAllByIds(experienceIds)
    }
}
