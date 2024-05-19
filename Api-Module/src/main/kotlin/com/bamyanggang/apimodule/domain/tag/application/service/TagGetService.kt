package com.bamyanggang.apimodule.domain.tag.application.service

import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.apimodule.domain.tag.application.dto.GetTag
import com.bamyanggang.domainmodule.domain.tag.service.TagReader
import org.springframework.stereotype.Service
import java.util.*

@Service
class TagGetService(
    private val tagReader: TagReader
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
}
