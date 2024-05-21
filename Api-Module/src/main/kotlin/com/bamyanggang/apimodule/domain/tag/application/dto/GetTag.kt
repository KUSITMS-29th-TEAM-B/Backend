package com.bamyanggang.apimodule.domain.tag.application.dto

import java.util.*

class GetTag {
    data class Response(
        val tags: List<ParentTagDetail>
    )

    data class ParentTagDetail(
        val id: UUID,
        val name: String,
        val childTags: List<ChildTagDetail>
    )

    data class ChildTagDetail(
        val id: UUID,
        val name: String,
    )
}
