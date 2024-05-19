package com.bamyanggang.apimodule.domain.experience.application.dto

import com.bamyanggang.apimodule.domain.strongpoint.application.dto.GetStrongPoint.DetailStrongPoint
import java.time.LocalDateTime
import java.util.*

class DetailExperience {
    data class Response(
        val id: UUID,
        val title: String,
        val parentTagId: UUID,
        val childTagId: UUID,
        val strongPoints: List<DetailStrongPoint>,
        val contents: List<DetailExperienceContent>,
        val startedAt: LocalDateTime,
        val endedAt: LocalDateTime,
    )

    data class DetailExperienceContent(
        val question: String,
        val answer: String,
    )
}
