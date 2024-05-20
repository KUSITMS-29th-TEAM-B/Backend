package com.bamyanggang.apimodule.domain.experience.application.service

import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.apimodule.domain.experience.application.dto.ExperienceYear
import com.bamyanggang.apimodule.domain.experience.application.dto.GetExperience
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
    fun getExperienceDetailById(experienceId: UUID) : GetExperience.DetailExperience {
        val oneExperience = experienceReader.readExperience(experienceId)
        return createExperienceDetailResponse(oneExperience)
    }

    fun getAllYearsByExistExperience(): ExperienceYear.Response {
        val currentUserId = getAuthenticationPrincipal()

        val years = experienceReader.readAllYearsByExistExperience(currentUserId)
        val yearTagInfos = years.map { year ->
            val parentTagIds = experienceReader.readByUserIDAndYearDesc(year, currentUserId)
                .distinctBy { it.parentTagId }
                .map { it.parentTagId }

            val tagDetails = tagReader.readByIds(parentTagIds).map {
                ExperienceYear.TagDetail(
                    id = it.id,
                    name = it.name
                )
            }

            ExperienceYear.YearTagInfo(
                year,
                tagDetails
            )
        }

        return ExperienceYear.Response(
            years,
            yearTagInfos
        )
    }

    fun getExperienceByYearAndParentTag(year: Int, parentTagId: UUID): GetExperience.Response {
        val experiences = experienceReader.readByYearAndTagId(year, parentTagId).map {
            createExperienceDetailResponse(it)
        }

        return GetExperience.Response(experiences)
    }

    fun getExperienceByYearAndChildTag(year: Int, childTagId: UUID): GetExperience.Response {
        val experiences = experienceReader.readByYearAndTagId(year, childTagId).map {
            createExperienceDetailResponse(it)
        }

        return GetExperience.Response(experiences)
    }

    private fun createExperienceDetailResponse(experience: Experience): GetExperience.DetailExperience {
        val detailExperienceContents = convertDetailExperienceContent(experience)
        val strongPointDetails = convertStrongPoints(experience)
        val detailParentTag = convertParentTag(experience)
        val detailChildTag = convertChildTag(experience)

        return GetExperience.DetailExperience(
            id = experience.id,
            title = experience.title,
            parentTag = detailParentTag,
            childTag = detailChildTag,
            strongPoints = strongPointDetails,
            contents = detailExperienceContents,
            startedAt = experience.startedAt,
            endedAt = experience.endedAt
        )
    }

    private fun convertChildTag(oneExperience: Experience) =
        tagReader.readById(oneExperience.childTagId).let {
            GetExperience.DetailTag(
                it.id,
                it.name
            )
        }

    private fun convertParentTag(oneExperience: Experience) =
        tagReader.readById(oneExperience.parentTagId).let {
            GetExperience.DetailTag(
                it.id,
                it.name
            )
        }

    private fun convertDetailExperienceContent(experience: Experience) =
        experience.contents.map { GetExperience.DetailExperienceContent(
                it.question,
                it.answer
            )
        }

    private fun convertStrongPoints(experience: Experience) =
        experience.strongPoints.map { it.strongPointId }.let {
            strongPointReader.readByIds(it).map { strongPoint ->
                GetExperience.DetailStrongPoint(
                    strongPoint.id,
                    strongPoint.name
                )
            }
    }
}   
