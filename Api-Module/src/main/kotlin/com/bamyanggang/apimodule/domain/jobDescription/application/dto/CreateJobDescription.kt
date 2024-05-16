package com.bamyanggang.apimodule.domain.jobDescription.application.dto

import java.time.LocalDateTime
import java.util.UUID

class CreateJobDescription {

    data class Request(
        val enterpriseName: String,
        val title: String,
        val content: String,
        val link: String,
        val startedAt: LocalDateTime,
        val endedAt: LocalDateTime
    )

    data class Response(
        val jobDescriptionId: UUID
    )

}
