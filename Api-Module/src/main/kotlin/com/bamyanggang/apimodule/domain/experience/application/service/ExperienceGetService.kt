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
import com.bamyanggang.domainmodule.domain.strongpoint.service.KeywordReader
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
    private val bookMarkReader: BookmarkReader,
    private val keywordReader: KeywordReader,
) {
    @Transactional(readOnly = true)
    fun getExperienceDetailById(experienceId: UUID): GetExperience.DetailExperience {
        val oneExperience = experienceReader.readExperience(experienceId)
        return createExperienceDetailResponse(oneExperience)
    }

    @Transactional(readOnly = true)
    fun getAllYearsByExistExperience(): ExperienceYear.Response {
        val currentUserId = getAuthenticationPrincipal()

        val years = experienceReader.readAllYearsByExistExperience(currentUserId)
        val yearTagInfos = years.map { year ->
            val parentTagIds = experienceReader.readByUserIdAndYearDesc(year, currentUserId)
                .distinctBy { it.parentTagId }
                .map { it.parentTagId }

            val tagDetails = tagReader.readByParentTagIds(parentTagIds).map {
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

    @Transactional(readOnly = true)
    fun getExperienceByYearAndParentTag(year: Int, parentTagId: UUID): GetExperience.Response {
        val experiences = experienceReader.readByYearAndParentTagId(year, parentTagId).map {
            createExperienceDetailResponse(it)
        }

        return GetExperience.Response(experiences)
    }

    @Transactional(readOnly = true)
    fun getExperienceByYearAndChildTag(year: Int, childTagId: UUID): GetExperience.Response {
        val experiences = experienceReader.readByChildTagIdAndYear(year, childTagId).map {
            createExperienceDetailResponse(it)
        }

        return GetExperience.Response(experiences)
    }

    @Transactional(readOnly = true)
    fun getAllBookmarkExperiences(jobDescriptionId: UUID): GetExperience.BookmarkResponse {
        val experienceIds =
            bookMarkReader.readByStatusAndJobDescriptionId(jobDescriptionId, BookmarkStatus.ON).map { it.experienceId }

        val userExperiences = experienceReader.readAllByUserId(getAuthenticationPrincipal())

        val bookmarkExperienceDetails = userExperiences.map {
            when {
                it.id in experienceIds -> createBookmarkExperienceDetailResponse(it, BookmarkStatus.ON)
                else -> createBookmarkExperienceDetailResponse(it, BookmarkStatus.OFF)
            }
        }

        return GetExperience.BookmarkResponse(bookmarkExperienceDetails)
    }

    @Transactional(readOnly = true)
    fun getBookmarkExperienceBySearch(jobDescriptionId: UUID, search: String): GetExperience.BookmarkResponse {
        val currentUserId = getAuthenticationPrincipal()

        val experiencesIds = experienceReader.readIdsByUserIdAndTitleContains(currentUserId, search) +
                experienceReader.readIdsByContentsContains(currentUserId, search) +
                tagReader.readIdsByUserIdAndNameContains(currentUserId, search).let {
                    experienceReader.readIdsByTagIds(it)
                } +
                strongPointReader.readIdsByUserIdAndNameContains(currentUserId, search).let {
                    experienceReader.readIdsByStrongPointIds(currentUserId, it)
                }

        val searchExperiences = experienceReader.readByIds(experiencesIds)
        val bookmarkExperienceIds =
            bookMarkReader.readByBookmarkStatusAndExperienceIds(experiencesIds, BookmarkStatus.ON)
                .map { it.experienceId }

        val bookmarkExperienceDetails = searchExperiences.map {
            when {
                it.id in bookmarkExperienceIds -> createBookmarkExperienceDetailResponse(it, BookmarkStatus.ON)
                else -> createBookmarkExperienceDetailResponse(it, BookmarkStatus.OFF)
            }
        }

        return GetExperience.BookmarkResponse(bookmarkExperienceDetails)
    }

    @Transactional(readOnly = true)
    fun getAllExperienceByYear(year: Int): GetExperience.Response {
        val experiences = experienceReader.readByYear(year).map {
            createExperienceDetailResponse(it)
        }

        return GetExperience.Response(experiences)
    }

    @Transactional(readOnly = true)
    fun getBookmarkExperienceFilterByTagId(
        jobDescriptionId: UUID,
        parentTagId: UUID?,
        childTagId: UUID?
    ): GetExperience.BookmarkResponse =
        when {
            parentTagId == null -> getAllBookmarkExperiences(jobDescriptionId)
            childTagId == null -> getBookmarkExperienceByParentTagId(jobDescriptionId, parentTagId)
            else -> getBookmarkExperienceByChildTagId(jobDescriptionId, childTagId)
        }

    @Transactional(readOnly = true)
    fun getBookmarkExperienceByParentTagId(
        jobDescriptionId: UUID,
        tagId: UUID?
    ): GetExperience.BookmarkResponse {
        val bookmarkDetailExperiences = getAllBookmarkExperiences(jobDescriptionId).experiences.filter {
            it.parentTag.id == tagId
        }

        return GetExperience.BookmarkResponse(bookmarkDetailExperiences)
    }

    @Transactional(readOnly = true)
    fun getBookmarkExperienceByChildTagId(
        jobDescriptionId: UUID,
        tagId: UUID?
    ): GetExperience.BookmarkResponse {
        val bookmarkDetailExperiences = getAllBookmarkExperiences(jobDescriptionId).experiences.filter {
            it.childTag.id == tagId
        }

        return GetExperience.BookmarkResponse(bookmarkDetailExperiences)
    }

    @Transactional(readOnly = true)
    fun getBookmarkExperience(
        jobDescriptionId: UUID,
        search: String?,
        parentTagId: UUID?,
        childTagId: UUID?,
    ): GetExperience.BookmarkResponse = when {
        search != null -> getBookmarkExperienceBySearch(jobDescriptionId, search.trim())
        else -> getBookmarkExperienceFilterByTagId(jobDescriptionId, parentTagId, childTagId)
    }

    @Transactional(readOnly = true)
    fun getExperienceFilter(year: Int, parentTagId: UUID?, childTagId: UUID?): GetExperience.Response =
        when {
            childTagId == null && parentTagId == null -> getAllExperienceByYear(year)
            childTagId == null && parentTagId != null -> getExperienceByYearAndParentTag(year, parentTagId)
            else -> childTagId?.let { getExperienceByYearAndChildTag(year, it) } ?: GetExperience.Response(emptyList())
        }

    private fun createExperienceDetailResponse(experience: Experience): GetExperience.DetailExperience {
        val detailExperienceContents = convertExperienceContent(experience.contents)
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
        val detailExperienceContents = convertExperienceContent(experience.contents)
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
            bookmarked = bookmarkStatus
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

    private fun convertExperienceContent(contents: List<ExperienceContent>) =
        contents.map { GetExperience.DetailExperienceContent(
                it.question,
                it.answer
            )
        }

    private fun convertStrongPoints(strongPoints: List<ExperienceStrongPoint>) : List<GetExperience.DetailStrongPoint> {
        val strongPointIds = strongPoints.map { it.strongPointId }
        val defaultStrongPoints = keywordReader.readByIds(strongPointIds)
        val targetStrongPointIds = strongPointIds.union(defaultStrongPoints.map { it.id }).toList()

        val customStrongPointDetails = strongPointReader.readByIds(targetStrongPointIds).map { strongPoint ->
            GetExperience.DetailStrongPoint(
                strongPoint.id,
                strongPoint.name
            )
        }

        val defaultStrongPointDetails = defaultStrongPoints.map {
            GetExperience.DetailStrongPoint(
                it.id,
                it.name
            )
        }

        return customStrongPointDetails + defaultStrongPointDetails
    }
}   
