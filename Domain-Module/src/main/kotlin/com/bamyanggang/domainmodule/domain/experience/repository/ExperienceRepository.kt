package com.bamyanggang.domainmodule.domain.experience.repository

import com.bamyanggang.domainmodule.domain.experience.aggregate.Experience
import java.util.*

interface ExperienceRepository {
    fun save(experience: Experience)
    fun deleteByExperienceId(experienceId: UUID)
    fun findByExperienceId(id: UUID): Experience
    fun findAllByUserId(userId: UUID): List<Experience>
}
