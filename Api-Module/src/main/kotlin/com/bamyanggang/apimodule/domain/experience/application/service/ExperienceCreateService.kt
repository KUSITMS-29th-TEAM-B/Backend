package com.bamyanggang.apimodule.domain.experience.application.service

import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.apimodule.domain.experience.application.dto.CreateExperience
import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceContent
import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceStrongPoint
import com.bamyanggang.domainmodule.domain.experience.service.ExperienceAppender
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ExperienceCreateService(
    val experienceAppender: ExperienceAppender
) {
    @Transactional
    fun createExperience(request: CreateExperience.Request):CreateExperience.Response {
        val currentUserId = getAuthenticationPrincipal()

        val newContents = request.contents.map {
            ExperienceContent.create(it.question, it.answer)
        }

        val newExperienceStrongPoints = request.strongPointIds.map {
            ExperienceStrongPoint.create(it)
        }

        return experienceAppender.appendExperience(
            title = request.title,
            userId = currentUserId,
            parentTagId = request.parentTagId,
            childTagId = request.childTagId,
            contents = newContents,
            experienceStrongPoints = newExperienceStrongPoints,
            startedAt = request.startedAt,
            endedAt = request.endedAt
        ).let {
            CreateExperience.Response(it.id)
        }
    }
}
