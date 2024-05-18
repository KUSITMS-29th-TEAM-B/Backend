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
    val experienceContentAppender: ExperienceContentAppender
) {
    @Transactional
    fun createExperience(request: CreateExperience.Request):CreateExperience.Response {
        val currentUserId = getAuthenticationPrincipal()

        return request.contents.map {
                    experienceContentAppender.appendExperienceContent(it.question, it.answer).id
                }.let {
                    experienceAppender.appendExperience(
                        title = request.title,
                        userId = currentUserId,
                        parentTagId = request.parentTagId,
                        childTagId = request.childTagId,
                        strongPointIds = request.strongPointIds,
                        contentIds = it,
                        startedAt = request.startedAt,
                        endedAt = request.endedAt
                    )
                }.let {
                    CreateExperience.Response(it.id)
                }
    }
}
