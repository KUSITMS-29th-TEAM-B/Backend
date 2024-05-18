package com.bamyanggang.domainmodule.domain.experience.service

import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceContent
import com.bamyanggang.domainmodule.domain.experience.repository.ExperienceContentRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExperienceContentAppender(
    private val experienceContentRepository: ExperienceContentRepository
) {
    fun appendExperienceContent(question: String, answer: String, experienceId: UUID): ExperienceContent {
        return ExperienceContent.create(question, answer, experienceId).also {
            experienceContentRepository.save(it)
        }
    }
}
