package com.bamyanggang.domainmodule.domain.experience.repository

import com.bamyanggang.domainmodule.domain.experience.aggregate.Experience
import java.util.*

interface ExperienceRepository {
    fun save(experience: Experience) : UUID
}
