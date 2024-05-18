package com.bamyanggang.domainmodule.domain.experience.service

import com.bamyanggang.domainmodule.domain.experience.aggregate.Experience
import com.bamyanggang.domainmodule.domain.experience.repository.ExperienceRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class ExperienceAppender(
    private val experienceRepository: ExperienceRepository,
) {
    fun appendExperience(title: String,
                         userId: UUID,
                         startedAt: LocalDateTime,
                         endedAt: LocalDateTime,
    ): Experience {
        return Experience.create(
            title = title,
            userId = userId,
            startedAt = startedAt,
            endedAt = endedAt
        ).also {
            experienceRepository.save(it)
        }
    }
}
