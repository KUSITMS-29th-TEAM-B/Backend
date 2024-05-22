package com.bamyanggang.domainmodule.domain.experience.service

import com.bamyanggang.domainmodule.domain.experience.aggregate.Experience
import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceContent
import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceStrongPoint
import com.bamyanggang.domainmodule.domain.experience.repository.ExperienceRepository
import java.time.LocalDateTime
import java.util.*

class ExperienceAppender(
    private val experienceRepository: ExperienceRepository,
) {
    fun appendExperience(title: String,
                         userId: UUID,
                         parentTagId : UUID,
                         childTagId : UUID,
                         contents : List<ExperienceContent>,
                         experienceStrongPoints: List<ExperienceStrongPoint>,
                         startedAt: LocalDateTime,
                         endedAt: LocalDateTime,
    ): Experience {
        return Experience.create(
            title = title,
            userId = userId,
            parentTagId = parentTagId,
            childTagId = childTagId,
            contents = contents,
            experienceStrongPoints = experienceStrongPoints,
            startedAt = startedAt,
            endedAt = endedAt
        ).also {
            experienceRepository.save(it)
        }
    }
}
