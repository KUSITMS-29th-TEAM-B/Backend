package com.bamyanggang.apimodule.domain.jobDescription.application.dto

class ApplyInfo {

    sealed class Request {
        data class Update(
            val contents: List<ContentInfo>
        ) : Request()

    }

    data class Response(
        val applyContentList: List<ContentInfo>
    )

    data class ContentInfo(
        val question: String,
        val answer: String
    )
}
