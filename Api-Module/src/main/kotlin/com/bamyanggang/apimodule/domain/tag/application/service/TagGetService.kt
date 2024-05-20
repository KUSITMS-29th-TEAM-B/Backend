package com.bamyanggang.apimodule.domain.tag.application.service

import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
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
    fun getAllParentTagByUserId(): GetTag.Response {
        val tagDetails = getAuthenticationPrincipal().let {
            tagReader.readAllParentTagsByUserId(it).map { tag ->
                GetTag.TagDetail(tag.id, tag.name)
            }
        }

        return GetTag.Response(tagDetails)
    }

    @Transactional(readOnly = true)
    fun getAllChildTagsByParentTagId(parentTagId: UUID): GetTag.Response {
        val tagDetails = getAuthenticationPrincipal().let {
            tagReader.readAllChildTagsByUserId(it, parentTagId).map { tag ->
                GetTag.TagDetail(tag.id, tag.name)
            }
        }

        return GetTag.Response(tagDetails)
    }

    @Transactional(readOnly = true)
    fun getParentTagsByYearAndLimit(year: Int, limit: Int): GetTag.Response {
        val currentUserId = getAuthenticationPrincipal()
        val topParentTagIds = experienceReader.readByYearDesc(year, currentUserId)
            .distinctBy { it.parentTagId }
            .take(limit)
            .map { it.parentTagId }

        return tagReader.readByIds(topParentTagIds).map {
            GetTag.TagDetail(it.id, it.name)
        }.let {
            GetTag.Response(it)
        }
    }

    @Transactional(readOnly = true)
    fun getAllParentTagsByYear(year: Int): GetTag.TotalTagInfo {
        val currentUserId = getAuthenticationPrincipal()
        val experiences = experienceReader.readByYearDesc(year, currentUserId)

        val experienceGroup = experiences.groupBy { it.parentTagId }

        val tagSummaries = experienceGroup.map {
            val parentTag = tagReader.readById(it.key)
            val strongPoints = TreeSet<UUID>()

            it.value.forEach { experience ->
                experience.strongPoints.forEach { strongPoint ->
                    strongPoints.add(strongPoint.id)
                }
            }

            GetTag.TagSummary(
                parentTag.id,
                parentTag.name,
                strongPoints.size,
                it.value.size
            )
        }

        return GetTag.TotalTagInfo(
            experiences.size,
            tagSummaries
        )
    }
}
