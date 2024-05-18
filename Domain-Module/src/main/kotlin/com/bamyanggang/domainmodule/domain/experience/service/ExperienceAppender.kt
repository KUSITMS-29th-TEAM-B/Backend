package com.bamyanggang.domainmodule.domain.experience.service

import com.bamyanggang.domainmodule.domain.experience.aggregate.Experience
import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceContent
import com.bamyanggang.domainmodule.domain.experience.aggregate.StrongPointInfo
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
                         parentTagId : UUID,
                         childTagId : UUID,
                         contents : List<ExperienceContent>,
                         strongPointInfos: List<StrongPointInfo>,
                         startedAt: LocalDateTime,
                         endedAt: LocalDateTime,
    ): Experience {
        return Experience.create(
            title = title,
            userId = userId,
            parentTagId = parentTagId,
            childTagId = childTagId,
            contents = contents,
            strongPointInfos = strongPointInfos,
            startedAt = startedAt,
            endedAt = endedAt
        ).also {
            experienceRepository.save(it)
        }
    }
}
