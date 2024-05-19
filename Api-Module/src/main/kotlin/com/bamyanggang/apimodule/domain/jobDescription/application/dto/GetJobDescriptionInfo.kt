package com.bamyanggang.apimodule.domain.jobDescription.application.dto

import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import java.time.LocalDateTime
import java.util.UUID

class GetJobDescriptionInfo {

    data class Response(
        val jobDescriptionId: UUID,
        val remainingDate: Int,
        val enterpriseName: String,
        val title: String,
        val writeStatus: WriteStatus,
        val createdAt: LocalDateTime,
        val startedAt: LocalDateTime,
        val endedAt: LocalDateTime,
    )

}
