package com.bamyanggang.apimodule.domain.experience.application.dto

import java.time.LocalDateTime
import java.util.*

class CreateExperience {
    data class Request(
        val title: String,
        val parentTagId: UUID,
        val childTagId: UUID,
        val strongPointIds: List<UUID>,
        val contents: List<ExperienceContentRequest>,
        val startedAt: LocalDateTime,
        val endedAt: LocalDateTime,
    )

    data class Response(val id: UUID)

    data class ExperienceContentRequest(
        val question: String,
        val answer: String,
    )
}
