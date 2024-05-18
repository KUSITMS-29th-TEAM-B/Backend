package com.bamyanggang.domainmodule.domain.experience.service

import com.bamyanggang.domainmodule.domain.experience.aggregate.Experience
import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceContent
import com.bamyanggang.domainmodule.domain.experience.aggregate.StrongPointInfo
import com.bamyanggang.domainmodule.domain.experience.repository.ExperienceRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class ExperienceModifier(
    private val experienceRepository: ExperienceRepository
) {
    fun modifyExperience(
        experienceId: UUID,
        userId: UUID,
        title: String,
        parentTagId: UUID,
        childTagId: UUID,
        contents: List<ExperienceContent>,
        strongPointInfos: List<StrongPointInfo>,
        startedAt: LocalDateTime,
        endedAt: LocalDateTime,
    ): Experience {
        return experienceRepository
                .findByExperienceId(experienceId)
                .update(
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
