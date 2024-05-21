package com.bamyanggang.apimodule.domain.jobDescription.application.dto

import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus

class ApplyInfo {

    sealed class Request {
        data class Update(
            val contents: List<ContentInfo>
        ) : Request()

    }

    data class Response(
        val applyContentList: List<ContentInfo>,
        val writeStatus: WriteStatus
    )

    data class ContentInfo(
        val question: String,
        val answer: String
    )
}
