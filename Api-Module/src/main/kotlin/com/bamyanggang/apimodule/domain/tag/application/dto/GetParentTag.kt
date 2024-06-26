package com.bamyanggang.apimodule.domain.tag.application.dto

import java.util.*

class GetParentTag {
    data class Response(
        val tags: List<TagDetail>
    )

    data class TagDetail(
        val id: UUID,
        val name: String
    )

    data class TotalTagInfo(
        val totalExperienceCount: Int,
        val tagInfos : List<ParentTagSummary>
    )

    data class ParentTagSummary(
        val id: UUID,
        val name: String,
        val strongPointCount: Int,
        val experienceCount: Int
    )

    data class Years(
        val years: List<Int>
    )
}
