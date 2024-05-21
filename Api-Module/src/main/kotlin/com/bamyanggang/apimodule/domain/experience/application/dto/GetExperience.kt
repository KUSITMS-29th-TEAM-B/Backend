package com.bamyanggang.apimodule.domain.experience.application.dto

import java.time.LocalDateTime
import java.util.*

class GetExperience {
    data class Response(
        val experiences: List<DetailExperience>
    )

    data class DetailExperience(
        val id: UUID,
        val title: String,
        val parentTag: DetailTag,
        val childTag: DetailTag,
        val strongPoints: List<DetailStrongPoint>,
        val contents: List<DetailExperienceContent>,
        val startedAt: LocalDateTime,
        val endedAt: LocalDateTime,
    )

    data class BookmarkResponse(
        val experience: List<BookmarkDetailExperience>
    )

    data class BookmarkDetailExperience(
        val id: UUID,
        val title: String,
        val parentTag: DetailTag,
        val childTag: DetailTag,
        val strongPoints: List<DetailStrongPoint>,
        val contents: List<DetailExperienceContent>,
        val startedAt: LocalDateTime,
        val endedAt: LocalDateTime,
        val bookmarked: Boolean,
    )


    data class DetailExperienceContent(
        val question: String,
        val answer: String,
    )

    data class DetailTag(
        val id: UUID,
        val name: String,
    )

    data class DetailStrongPoint(
        val id: UUID,
        val name: String,
    )
}
