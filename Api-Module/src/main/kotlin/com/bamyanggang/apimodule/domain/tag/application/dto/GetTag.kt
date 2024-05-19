package com.bamyanggang.apimodule.domain.tag.application.dto

import java.util.*

class GetTag {
    data class Response(
        val tags: List<TagDetail>
    )

    data class TagDetail(
        val id: UUID,
        val name: String
    )
}
