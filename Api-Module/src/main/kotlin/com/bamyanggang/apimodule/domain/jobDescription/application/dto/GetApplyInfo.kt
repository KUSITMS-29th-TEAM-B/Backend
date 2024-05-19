package com.bamyanggang.apimodule.domain.jobDescription.application.dto

class GetApplyInfo {

    data class Response(
        val applyContentList: List<ContentInfo>
    )

    data class ContentInfo(
        val question: String,
        val answer: String
    )
}
