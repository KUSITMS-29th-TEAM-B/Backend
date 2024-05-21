package com.bamyanggang.apimodule.domain.experience.application.dto

import java.util.*

class ExperienceYear {
    data class Response(
        val years : List<Int>,
        val yearTagInfos: List<YearTagInfo>
    )

    data class YearTagInfo(
        val year: Int,
        val tags: List<TagDetail>
    )

    data class TagDetail(
        val id: UUID,
        val name: String,
    )
}
