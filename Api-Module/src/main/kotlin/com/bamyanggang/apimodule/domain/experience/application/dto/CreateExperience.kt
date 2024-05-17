package com.bamyanggang.apimodule.domain.experience.application.dto

import java.time.LocalDateTime
import java.util.*

class CreateExperience {
    data class Request(
        val title: String,
        val startedAt: LocalDateTime,
        val endedAt: LocalDateTime,
        val parentTag: UUID,
        val childTag: UUID,
        val strongPoints: List<UUID>,
        val contents: List<ExperienceContentRequest>,
    )

    data class Response(val id: UUID)

    data class ExperienceContentRequest(
        val question: String,
        val answer: String,
    )
}
