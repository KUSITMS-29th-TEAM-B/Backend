package com.bamyanggang.apimodule.domain.tag.application.dto

import com.bamyanggang.apimodule.domain.tag.application.dto.GetParentTag.TagDetail
import java.util.*

class GetChildTag {
    data class Response(
        val tags: List<TagDetail>
    )

    data class TotalTagInfo(
        val totalExperienceCount: Int,
        val tagInfos : List<ChildTagSummary>
    )

    data class ChildTagSummary(
        val id: UUID,
        val name: String,
        val experienceCount: Int
    )
}
