package com.bamyanggang.apimodule.domain.jobDescription.application.dto

import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import java.time.LocalDateTime
import java.util.UUID

class JobDescriptionInfo {

    sealed class Request {
        data class Update(
            val enterpriseName: String?,
            val title: String?,
            val content: String?,
            val link: String?,
            val startedAt: LocalDateTime?,
            val endedAt: LocalDateTime?
        ) : Request()

    }

    sealed class Response {
        data class Basic(
            val jobDescriptionId: UUID,
            val remainingDate: Int,
            val enterpriseName: String,
            val title: String,
            val writeStatus: WriteStatus,
            val createdAt: LocalDateTime,
            val startedAt: LocalDateTime,
            val endedAt: LocalDateTime,
        ) : Response()

        data class Detail(
            val remainingDate: Int,
            val enterpriseName: String,
            val title: String,
            val content: String,
            val link: String,
            val writeStatus: WriteStatus,
            val createdAt: LocalDateTime,
            val startedAt: LocalDateTime,
            val endedAt: LocalDateTime,
            val isApplyExists: Boolean
        ): Response()
    }

}
