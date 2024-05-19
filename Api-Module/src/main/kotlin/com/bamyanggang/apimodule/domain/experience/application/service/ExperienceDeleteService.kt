package com.bamyanggang.apimodule.domain.experience.application.service

import com.bamyanggang.domainmodule.domain.experience.service.ExperienceRemover
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ExperienceDeleteService(
    val experienceRemover: ExperienceRemover
) {
    @Transactional
    fun deleteExperienceById(experienceId: UUID) {
        experienceRemover.remove(experienceId)
    }
}
