package com.bamyanggang.domainmodule.domain.experience.aggregate

import com.bamyanggang.domainmodule.common.entity.DomainEntity
import com.example.uuid.UuidCreator
import java.util.*

data class ExperienceContent(
    override val id: UUID = UuidCreator.create(),
    val question : String,
    val answer : String,
) : DomainEntity {

    companion object {
        fun create(question: String, answer: String): ExperienceContent {
            return ExperienceContent(UuidCreator.create(), question, answer)
        }

        fun toDomain(id: UUID, question: String, answer: String): ExperienceContent {
            return ExperienceContent(id, question, answer)
        }
    }
}