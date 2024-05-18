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
                         parentTagId: UUID,
                         childTagId: UUID,
                         strongPointIds: List<UUID>,
                         contentIds: List<UUID>,
                         startedAt: LocalDateTime,
                         endedAt: LocalDateTime,
    ): Experience {
        return Experience.create(
            title = title,
            userId = userId,
            parentTagId = parentTagId,
            childTagId = childTagId,
            strongPointIds = strongPointIds,
            contentIds = contentIds,
            startedAt = startedAt,
            endedAt = endedAt
        ).also {
            experienceRepository.save(it)
        }
    }
}
