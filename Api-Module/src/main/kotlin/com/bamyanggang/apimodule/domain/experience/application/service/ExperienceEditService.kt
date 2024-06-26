package com.bamyanggang.apimodule.domain.experience.application.service

import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.apimodule.domain.experience.application.dto.EditExperience
import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceContent
import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceStrongPoint
import com.bamyanggang.domainmodule.domain.experience.service.ExperienceModifier
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ExperienceEditService(
    private val experienceModifier: ExperienceModifier,
) {
    @Transactional
    fun editExperienceById(request: EditExperience.Request, experienceId: UUID) : EditExperience.Response{
        val newContents = request.contents.map {
            ExperienceContent.create(it.question, it.answer)
        }

        val newExperienceStrongPoints = request.strongPointIds.map {
            ExperienceStrongPoint.create(it)
        }

        return getAuthenticationPrincipal().let {
            experienceModifier.modifyExperience(
                experienceId = experienceId,
                userId = it,
                title = request.title,
                parentTagId = request.parentTagId,
                childTagId = request.childTagId,
                contents = newContents,
                experienceStrongPoints = newExperienceStrongPoints,
                startedAt = request.startedAt,
                endedAt = request.endedAt
            )
        }.let { EditExperience.Response(it.id) }
    }
}
