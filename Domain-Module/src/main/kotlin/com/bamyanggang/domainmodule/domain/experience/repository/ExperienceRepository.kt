package com.bamyanggang.domainmodule.domain.experience.repository

import com.bamyanggang.domainmodule.domain.experience.aggregate.Experience
import java.util.*

interface ExperienceRepository {
    fun save(experience: Experience)
    fun deleteByExperienceId(experienceId: UUID)
    fun findByExperienceId(id: UUID): Experience
    fun findAllByUserId(userId: UUID): List<Experience>
    fun findByUserIdAndYearDesc(year: Int, userId: UUID): List<Experience>
    fun findByYearAndParentTagId(year: Int, parentTagId: UUID): List<Experience>
    fun findByYearAndChildTagId(year: Int, childTagId: UUID): List<Experience>
}
