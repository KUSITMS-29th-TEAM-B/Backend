package com.bamyanggang.apimodule.domain.experience.application.service

import com.bamyanggang.apimodule.domain.experience.application.dto.DetailExperience
import com.bamyanggang.apimodule.domain.strongpoint.application.dto.GetStrongPoint
import com.bamyanggang.domainmodule.domain.experience.service.ExperienceReader
import com.bamyanggang.domainmodule.domain.strongpoint.service.StrongPointReader
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExperienceGetService(
    private val experienceReader: ExperienceReader,
    private val strongPointReader: StrongPointReader,
) {
    fun getExperienceDetailById(experienceId: UUID) : DetailExperience.Response {
        val oneExperience = experienceReader.readExperience(experienceId)

        val detailExperienceContents = oneExperience.contents.map {
            DetailExperience.DetailExperienceContent(
                it.question,
                it.answer
            )
        }

        val strongPointIds = oneExperience.strongPoints.map { it.id }
        val strongPointDetails = strongPointReader.readByIds(strongPointIds).map {
            GetStrongPoint.DetailStrongPoint(
                it.id,
                it.name
            )
        }

        return oneExperience.let {
            DetailExperience.Response(
                id = it.id,
                title = it.title,
                parentTagId = it.parentTagId,
                childTagId = it.childTagId,
                strongPoints = strongPointDetails,
                contents = detailExperienceContents,
                startedAt = it.startedAt,
                endedAt = it.endedAt
            )
        }
    }
}
