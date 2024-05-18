package com.bamyanggang.apimodule.domain.experience.application.service

import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.apimodule.domain.experience.application.dto.CreateExperience
import com.bamyanggang.domainmodule.domain.experience.aggregate.StrongPointInfo
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

        val newContents = request.contents.map {
            experienceContentAppender.appendExperienceContent(it.question, it.answer)
        }

        val newStrongPointInfos = request.strongPointIds.map {
            StrongPointInfo.create(it)
        }

        return experienceAppender.appendExperience(
            title = request.title,
            userId = currentUserId,
            parentTagId = request.parentTagId,
            childTagId = request.childTagId,
            contents = newContents,
            strongPointInfos = newStrongPointInfos,
            startedAt = request.startedAt,
            endedAt = request.endedAt
        ).let {
            CreateExperience.Response(it.id)
        }
    }
}
