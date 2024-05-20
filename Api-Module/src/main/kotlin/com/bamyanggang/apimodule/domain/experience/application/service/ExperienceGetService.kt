package com.bamyanggang.apimodule.domain.experience.application.service

import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.apimodule.domain.experience.application.dto.DetailExperience
import com.bamyanggang.apimodule.domain.experience.application.dto.ExperienceYear
import com.bamyanggang.domainmodule.domain.experience.service.ExperienceReader
import com.bamyanggang.domainmodule.domain.strongpoint.service.StrongPointReader
import com.bamyanggang.domainmodule.domain.tag.service.TagReader
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExperienceGetService(
    private val experienceReader: ExperienceReader,
    private val strongPointReader: StrongPointReader,
    private val tagReader: TagReader,
) {
    fun getExperienceDetailById(experienceId: UUID) : DetailExperience.Response {
        val oneExperience = experienceReader.readExperience(experienceId)

        val detailExperienceContents = oneExperience.contents.map {
            DetailExperience.DetailExperienceContent(
                it.question,
                it.answer
            )
        }

        val strongPointIds = oneExperience.strongPoints.map { it.strongPointId }
        val strongPointDetails = strongPointReader.readByIds(strongPointIds).map {
            DetailExperience.DetailStrongPoint(
                it.id,
                it.name
            )
        }

        val detailParentTag = tagReader.readById(oneExperience.parentTagId).let {
            DetailExperience.DetailTag(
                it.id,
                it.name
            )
        }

        val detailChildTag = tagReader.readById(oneExperience.childTagId).let {
            DetailExperience.DetailTag(
                it.id,
                it.name
            )
        }

        return oneExperience.let {
            DetailExperience.Response(
                id = it.id,
                title = it.title,
                parentTag = detailParentTag,
                childTag = detailChildTag,
                strongPoints = strongPointDetails,
                contents = detailExperienceContents,
                startedAt = it.startedAt,
                endedAt = it.endedAt
            )
        }
    }
    
    fun getAllYearsByExistExperience(): ExperienceYear.Response {
        val currentUserId = getAuthenticationPrincipal()

        return experienceReader.readAllYearsByExistExperience(currentUserId)
            .let { ExperienceYear.Response(it) }
    }
}   
