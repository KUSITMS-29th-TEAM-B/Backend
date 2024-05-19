package com.bamyanggang.domainmodule.domain.jobDescription.aggregate

import com.bamyanggang.domainmodule.common.entity.DomainEntity
import com.example.uuid.UuidCreator
import java.util.*

class ApplyContent(
    override val id: UUID = UuidCreator.create(),
    val question: String,
    val answer: String
): DomainEntity {

    init {
        require(question.isNotBlank()) { "질문은 필수입니다." }
        require(answer.isNotBlank()) { "답변은 필수입니다." }
    }

    companion object {
        fun create(
            question: String,
            answer: String,
        ): ApplyContent {
            return ApplyContent(
                question = question,
                answer = answer,
            )
        }
    }

}
