package com.bamyanggang.apimodule.domain.jobDescription.application.dto

class CreateApply {

    data class Request(
        val contents : List<CreateApplyContent>
    )

    data class CreateApplyContent (
        val question: String,
        val answer: String
    )

}
