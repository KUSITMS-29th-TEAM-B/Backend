package com.bamyanggang.domainmodule.domain.experience.aggregate

import com.bamyanggang.domainmodule.common.entity.DomainEntity
import com.example.uuid.UuidCreator
import java.util.*

data class ExperienceContent(
    override val id: UUID = UuidCreator.create(),
    val question: String,
    val answer: String,
    val experienceId: UUID
) : DomainEntity {

    companion object {
        fun create(question: String, answer: String, experienceId: UUID): ExperienceContent {
            return ExperienceContent(question = question, answer = answer, experienceId = experienceId)
        }

        fun toDomain(id: UUID, question: String, answer: String, experienceId: UUID): ExperienceContent {
            return ExperienceContent(id, question, answer, experienceId)
        }
    }
}
