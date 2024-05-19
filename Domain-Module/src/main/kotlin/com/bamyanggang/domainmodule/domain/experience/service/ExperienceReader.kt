package com.bamyanggang.domainmodule.domain.experience.service

import com.bamyanggang.domainmodule.domain.experience.aggregate.Experience
import com.bamyanggang.domainmodule.domain.experience.repository.ExperienceRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExperienceReader(
    private val experienceRepository: ExperienceRepository
) {
    fun readExperience(experienceId: UUID): Experience {
        return experienceRepository.findByExperienceId(experienceId)
    }

    fun readAllYearsByExistExperience(userId: UUID): List<Int> {
        val yearSet : TreeSet<Int> = TreeSet<Int>()
        readAllExperienceByUserId(userId).forEach {
            yearSet.add(it.startedAt.year)
        }

        return yearSet.toList()
    }

    fun readAllExperienceByUserId(userId: UUID): List<Experience> {
        return experienceRepository.findAllByUserId(userId)
    }
}
