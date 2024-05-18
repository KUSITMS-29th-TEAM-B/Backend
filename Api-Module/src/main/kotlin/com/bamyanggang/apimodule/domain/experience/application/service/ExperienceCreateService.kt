package com.bamyanggang.apimodule.domain.experience.application.service

import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.apimodule.domain.experience.application.dto.CreateExperience
import com.bamyanggang.domainmodule.domain.experience.service.ExperienceAppender
import com.bamyanggang.domainmodule.domain.experience.service.ExperienceContentAppender
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ExperienceCreateService(
    val experienceAppender: ExperienceAppender,
    val experienceContentAppender: ExperienceContentAppender,
) {
    @Transactional
    fun createExperience(request: CreateExperience.Request):CreateExperience.Response {
        val currentUserId = getAuthenticationPrincipal()

        val newExperience = experienceAppender.appendExperience(
            title = request.title,
            userId = currentUserId,
            startedAt = request.startedAt,
            endedAt = request.endedAt
        )

        request.contents.forEach {
            experienceContentAppender.appendExperienceContent(it.question, it.answer, newExperience.id)
        }

        return CreateExperience.Response(newExperience.id)
    }
}
