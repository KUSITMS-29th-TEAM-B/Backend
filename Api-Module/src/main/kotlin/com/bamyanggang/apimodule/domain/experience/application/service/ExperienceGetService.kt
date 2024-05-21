package com.bamyanggang.apimodule.domain.experience.application.service

import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.apimodule.domain.experience.application.dto.ExperienceYear
import com.bamyanggang.apimodule.domain.experience.application.dto.GetExperience
import com.bamyanggang.domainmodule.domain.bookmark.enums.BookmarkStatus
import com.bamyanggang.domainmodule.domain.bookmark.service.BookmarkReader
import com.bamyanggang.domainmodule.domain.experience.aggregate.Experience
import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceContent
import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceStrongPoint
import com.bamyanggang.domainmodule.domain.experience.service.ExperienceReader
import com.bamyanggang.domainmodule.domain.strongpoint.service.StrongPointReader
import com.bamyanggang.domainmodule.domain.tag.service.TagReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ExperienceGetService(
    private val experienceReader: ExperienceReader,
    private val strongPointReader: StrongPointReader,
    private val tagReader: TagReader,
    private val bookMarkReader: BookmarkReader
) {
    @Transactional(readOnly = true)
    fun getExperienceDetailById(experienceId: UUID) : GetExperience.DetailExperience {
        val oneExperience = experienceReader.readExperience(experienceId)
        return createExperienceDetailResponse(oneExperience)
    }

    @Transactional(readOnly = true)
    fun getAllYearsByExistExperience(): ExperienceYear.Response {
        val currentUserId = getAuthenticationPrincipal()

        return experienceReader.readAllYearsByExistExperience(currentUserId)
            .let { ExperienceYear.Response(it) }
    }

    @Transactional(readOnly = true)
    fun getExperienceByYearAndParentTag(year: Int, parentTagId: UUID): GetExperience.Response {
        val experiences = experienceReader.readByYearAndParentTagId(year, parentTagId).map {
            createExperienceDetailResponse(it)
        }

        return GetExperience.Response(experiences)
    }

    @Transactional(readOnly = true)
    fun getExperienceByYearAndChildTag(year: Int, childTagId: UUID): GetExperience.Response {
        val experiences = experienceReader.readByYearAndChildTagId(year, childTagId).map {
            createExperienceDetailResponse(it)
        }

        return GetExperience.Response(experiences)
    }

    @Transactional(readOnly = true)
    fun getAllBookmarkExperiences(jobDescriptionId: UUID): GetExperience.BookmarkResponse {
        val experienceIds = bookMarkReader.readByStatusAndJobDescriptionId(jobDescriptionId, BookmarkStatus.ON).map { it.experienceId }

        val userExperiences = experienceReader.readAllByUserId(getAuthenticationPrincipal())

        val bookmarkExperienceDetails = userExperiences.map {
            when {
                it.id in experienceIds -> createBookmarkExperienceDetailResponse(it, BookmarkStatus.ON)
                else -> createBookmarkExperienceDetailResponse(it, BookmarkStatus.OFF)
            }
        }

        return GetExperience.BookmarkResponse(bookmarkExperienceDetails)
    }

    private fun createExperienceDetailResponse(experience: Experience): GetExperience.DetailExperience {
        val detailExperienceContents = convertDetailExperienceContent(experience.contents)
        val strongPointDetails = convertStrongPoints(experience.strongPoints)
        val detailParentTag = convertParentTag(experience.parentTagId)
        val detailChildTag = convertChildTag(experience.childTagId)

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

    private fun createBookmarkExperienceDetailResponse(experience: Experience, bookmarkStatus: BookmarkStatus): GetExperience.BookmarkDetailExperience {
        val detailExperienceContents = convertDetailExperienceContent(experience.contents)
        val strongPointDetails = convertStrongPoints(experience.strongPoints)
        val detailParentTag = convertParentTag(experience.parentTagId)
        val detailChildTag = convertChildTag(experience.childTagId)

        return GetExperience.BookmarkDetailExperience(
            id = experience.id,
            title = experience.title,
            parentTag = detailParentTag,
            childTag = detailChildTag,
            strongPoints = strongPointDetails,
            contents = detailExperienceContents,
            startedAt = experience.startedAt,
            endedAt = experience.endedAt,
            bookmarked = bookmarkStatus.boolStatus
        )
    }

    private fun convertChildTag(childTagId: UUID) =
        tagReader.readById(childTagId).let {
            GetExperience.DetailTag(
                it.id,
                it.name
            )
        }

    private fun convertParentTag(parentTagId: UUID) =
        tagReader.readById(parentTagId).let {
            GetExperience.DetailTag(
                it.id,
                it.name
            )
        }

    private fun convertDetailExperienceContent(contents: List<ExperienceContent>) =
        contents.map { GetExperience.DetailExperienceContent(
                it.question,
                it.answer
            )
        }

    private fun convertStrongPoints(strongPoints: List<ExperienceStrongPoint>) =
        strongPoints.map { it.strongPointId }.let {
            strongPointReader.readByIds(it).map { strongPoint ->
                GetExperience.DetailStrongPoint(
                    strongPoint.id,
                    strongPoint.name
                )
            }
    }

    fun getBookmarkExperienceBySearch(search: String): GetExperience.BookmarkResponse {
        return GetExperience.BookmarkResponse(emptyList())
    }
}   
