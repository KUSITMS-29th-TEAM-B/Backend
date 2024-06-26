package com.bamyanggang.domainmodule.domain.experience.service

import com.bamyanggang.domainmodule.domain.experience.aggregate.Experience
import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceContent
import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceStrongPoint
import com.bamyanggang.domainmodule.domain.experience.repository.ExperienceRepository
import java.time.LocalDateTime
import java.util.*

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
        experienceStrongPoints: List<ExperienceStrongPoint>,
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
                    experienceStrongPoints = experienceStrongPoints,
                    startedAt = startedAt,
                    endedAt = endedAt
                ).also {
                    experienceRepository.save(it)
                }
    }
}
