package com.bamyanggang.apimodule.domain.tag.application.service

import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.apimodule.domain.tag.application.dto.GetChildTag
import com.bamyanggang.apimodule.domain.tag.application.dto.GetParentTag
import com.bamyanggang.apimodule.domain.tag.application.dto.GetTag
import com.bamyanggang.domainmodule.domain.experience.service.ExperienceReader
import com.bamyanggang.domainmodule.domain.tag.service.TagReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class TagGetService(
    private val tagReader: TagReader,
    private val experienceReader: ExperienceReader
) {
    @Transactional(readOnly = true)
    fun getAllParentTagByUserId(): GetParentTag.Response {
        val tagDetails = getAuthenticationPrincipal().let {
            tagReader.readAllParentTagsByUserId(it).map { tag ->
                GetParentTag.TagDetail(tag.id, tag.name)
            }
        }

        return GetParentTag.Response(tagDetails)
    }

    @Transactional(readOnly = true)
    fun getAllChildTagsByParentTagId(parentTagId: UUID): GetChildTag.Response {
        val tagDetails = getAuthenticationPrincipal().let {
            tagReader.readAllChildTagsByUserId(it, parentTagId).map { tag ->
                GetParentTag.TagDetail(tag.id, tag.name)
            }
        }

        return GetChildTag.Response(tagDetails)
    }

    @Transactional(readOnly = true)
    fun getParentTagsByYearAndLimit(year: Int, limit: Int): GetParentTag.Response {
        val currentUserId = getAuthenticationPrincipal()
        val topParentTagIds = experienceReader.readByUserIDAndYearDesc(year, currentUserId)
            .distinctBy { it.parentTagId }
            .take(limit)
            .map { it.parentTagId }

        return tagReader.readByIds(topParentTagIds).map {
            GetParentTag.TagDetail(it.id, it.name)
        }.let {
            GetParentTag.Response(it)
        }
    }

    @Transactional(readOnly = true)
    fun getAllParentTagsByYear(year: Int): GetParentTag.TotalTagInfo {
        val currentUserId = getAuthenticationPrincipal()
        val experiences = experienceReader.readByUserIDAndYearDesc(year, currentUserId)

        val experienceGroup = experiences.groupBy { it.parentTagId }

        val tagSummaries = experienceGroup.map {
            val parentTag = tagReader.readById(it.key)
            val strongPoints = TreeSet<UUID>()

            it.value.forEach { experience ->
                experience.strongPoints.forEach { strongPoint ->
                    strongPoints.add(strongPoint.id)
                }
            }

            GetParentTag.ParentTagSummary(
                parentTag.id,
                parentTag.name,
                strongPoints.size,
                it.value.size
            )
        }

        return GetParentTag.TotalTagInfo(
            experiences.size,
            tagSummaries
        )
    }

    @Transactional(readOnly = true)
    fun getAllChildTagsByYearAndParentTagId(year: Int, parentTagId: UUID): GetChildTag.TotalTagInfo {
        val currentUserId = getAuthenticationPrincipal()
        val experiences = experienceReader.readByUserIDAndYearDesc(year, currentUserId)
        val experienceGroup = experiences.groupBy { it.childTagId }

        val tagSummaries = experienceGroup.map {
            val childTag = tagReader.readById(it.key)

            GetChildTag.ChildTagSummary(
                childTag.id,
                childTag.name,
                it.value.size
            )
        }

        return GetChildTag.TotalTagInfo(
            experiences.size,
            tagSummaries
        )
    }

    @Transactional(readOnly = true)
    fun getAllYearsByParentTagId(parentTagId: UUID): GetParentTag.Years {
        val experiences = getAuthenticationPrincipal().let {
            experienceReader.readByUserIdAndParentTagId(it, parentTagId)
        }

        val years = experiences
            .distinctBy { it.createdAt.year }
            .map { it.createdAt.year }
            .sorted().reversed()

        return GetParentTag.Years(years)
    }

    @Transactional(readOnly = true)
    fun getAllTags(): GetTag.Response {
        val parentTags = getAuthenticationPrincipal().let {
            tagReader.readAllParentTagsByUserId(it)
        }

        val parentTagDetails = parentTags.map {
            val childTagDetails = tagReader.readChildTagsByParentTagId(it.id).map {
                GetTag.ChildTagDetail(
                    it.id,
                    it.name
                )
            }

            GetTag.ParentTagDetail(
                it.id,
                it.name,
                childTagDetails
            )
        }

        return GetTag.Response(parentTagDetails)
    }
}
