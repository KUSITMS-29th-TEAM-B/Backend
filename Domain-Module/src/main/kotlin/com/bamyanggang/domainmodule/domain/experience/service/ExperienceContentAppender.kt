package com.bamyanggang.domainmodule.domain.experience.service

import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceContent
import com.bamyanggang.domainmodule.domain.experience.repository.ExperienceContentRepository
import org.springframework.stereotype.Service

@Service
class ExperienceContentAppender(
    private val experienceContentRepository: ExperienceContentRepository
) {
    fun appendExperienceContent(question: String, answer: String): ExperienceContent {
        return ExperienceContent.create(question, answer).also {
            experienceContentRepository.save(it)
        }
    }
}
