package com.bamyanggang.domainmodule.domain.experience.repository

import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceContent
import java.util.*

interface ExperienceContentRepository {
    fun save(experienceContent: ExperienceContent)
    fun findByExperienceId(experienceId: UUID) : List<ExperienceContent>
    fun deleteAllByIds(experienceContents: List<ExperienceContent>)
}
