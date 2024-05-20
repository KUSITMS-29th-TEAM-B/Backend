package com.bamyanggang.apimodule.domain.experience.application.service

import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.apimodule.domain.experience.application.dto.DetailExperience
import com.bamyanggang.apimodule.domain.experience.application.dto.ExperienceYear
import com.bamyanggang.domainmodule.domain.experience.aggregate.Experience
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
        return createExperienceDetailResponse(oneExperience)
    }

    fun getAllYearsByExistExperience(): ExperienceYear.Response {
        val currentUserId = getAuthenticationPrincipal()

        return experienceReader.readAllYearsByExistExperience(currentUserId)
            .let { ExperienceYear.Response(it) }
    }

    fun getExperienceByYearAndParentTag(year: Int, parentTagId: UUID): List<DetailExperience.Response> {
        return experienceReader.readByYearAndParentTagId(year, parentTagId).map {
            createExperienceDetailResponse(it)
        }
    }

    fun getExperienceByYearAndChildTag(year: Int, childTagId: UUID): List<DetailExperience.Response> {
        return experienceReader.readByYearAndChildTagId(year, childTagId).map {
            createExperienceDetailResponse(it)
        }
    }

    private fun createExperienceDetailResponse(it: Experience): DetailExperience.Response {
        val detailExperienceContents = convertDetailExperienceContent(it)
        val strongPointDetails = convertStrongPoints(it)
        val detailParentTag = convertParentTag(it)
        val detailChildTag = convertChildTag(it)

        return DetailExperience.Response(
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

    private fun convertChildTag(oneExperience: Experience) =
        tagReader.readById(oneExperience.childTagId).let {
            DetailExperience.DetailTag(
                it.id,
                it.name
            )
        }

    private fun convertParentTag(oneExperience: Experience) =
        tagReader.readById(oneExperience.parentTagId).let {
            DetailExperience.DetailTag(
                it.id,
                it.name
            )
        }

    private fun convertDetailExperienceContent(experience: Experience) =
        experience.contents.map { DetailExperience.DetailExperienceContent(
                it.question,
                it.answer
            )
        }

    private fun convertStrongPoints(experience: Experience) =
        experience.strongPoints.map { it.strongPointId }.let {
            strongPointReader.readByIds(it).map { strongPoint ->
                DetailExperience.DetailStrongPoint(
                    strongPoint.id,
                    strongPoint.name
                )
            }
    }
}   
