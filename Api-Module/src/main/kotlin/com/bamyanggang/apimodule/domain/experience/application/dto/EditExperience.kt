package com.bamyanggang.apimodule.domain.experience.application.dto

import com.bamyanggang.apimodule.domain.experience.application.dto.CreateExperience.ExperienceContentRequest
import java.time.LocalDateTime
import java.util.*

class EditExperience {
    data class Request(
        val title: String,
        val parentTagId: UUID,
        val childTagId: UUID,
        val strongPointIds: List<UUID>,
        val contents: List<ExperienceContentRequest>,
        val startedAt: LocalDateTime,
        val endedAt: LocalDateTime,
    )

    data class ExperienceContentRequest(
        val question: String,
        val answer: String,
    )

    data class Response(val id: UUID)
}
