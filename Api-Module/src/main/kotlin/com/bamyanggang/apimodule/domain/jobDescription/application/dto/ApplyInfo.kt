package com.bamyanggang.apimodule.domain.jobDescription.application.dto

class ApplyInfo {

    data class Request(
        val contents: List<ContentInfo>
    )

    data class Response(
        val applyContentList: List<ContentInfo>
    )

    data class ContentInfo(
        val question: String,
        val answer: String
    )
}
