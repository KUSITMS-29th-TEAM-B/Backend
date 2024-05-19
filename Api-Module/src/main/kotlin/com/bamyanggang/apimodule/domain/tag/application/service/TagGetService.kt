package com.bamyanggang.apimodule.domain.tag.application.service

import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.apimodule.domain.tag.application.dto.GetTag
import com.bamyanggang.domainmodule.domain.experience.service.ExperienceReader
import com.bamyanggang.domainmodule.domain.tag.service.TagReader
import org.springframework.stereotype.Service
import java.util.*

@Service
class TagGetService(
    private val tagReader: TagReader,
    private val experienceReader: ExperienceReader
) {
    fun getAllParentTagByUserId(): GetTag.Response {
        val tagDetails = getAuthenticationPrincipal().let {
            tagReader.readAllParentTagsByUserId(it).map { tag ->
                GetTag.TagDetail(tag.id, tag.name)
            }
        }

        return GetTag.Response(tagDetails)
    }

    fun getAllChildTagsByParentTagId(parentTagId: UUID): GetTag.Response {
        val tagDetails = getAuthenticationPrincipal().let {
            tagReader.readAllChildTagsByUserId(it, parentTagId).map { tag ->
                GetTag.TagDetail(tag.id, tag.name)
            }
        }

        return GetTag.Response(tagDetails)
    }

    fun getParentTagsByYearAndLimit(year: Int, limit: Int): GetTag.Response {
        val topParentTagIds = experienceReader.readByYearDesc(year)
            .distinctBy { it.parentTagId }
            .take(limit)
            .map { it.parentTagId }

        return tagReader.readByIds(topParentTagIds).map {
            GetTag.TagDetail(it.id, it.name)
        }.let {
            GetTag.Response(it)
        }
    }

    fun getAllParentTagsByYear(year: Int): GetTag.Response {
        experienceReader.readByYearDesc(year)
        return GetTag.Response(emptyList())
    }
}
