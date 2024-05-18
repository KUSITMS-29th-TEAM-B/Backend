package com.bamyanggang.domainmodule.domain.experience.repository

import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceContent

interface ExperienceContentRepository {
    fun save(experienceContent: ExperienceContent)
}
