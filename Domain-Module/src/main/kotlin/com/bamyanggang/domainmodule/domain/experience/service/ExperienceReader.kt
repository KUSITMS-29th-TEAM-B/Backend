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
        readAllByUserId(userId).forEach {
            yearSet.add(it.startedAt.year)
        }

        return yearSet.toList()
    }

    fun readAllByUserId(userId: UUID): List<Experience> {
        return experienceRepository.findAllByUserId(userId)
    }

    fun readByUserIDAndYearDesc(year: Int, userId: UUID): List<Experience> {
        return experienceRepository.findByUserIdAndYearDesc(year, userId)
    }

    fun readByYearAndParentTagId(year: Int, parentTagId: UUID) : List<Experience> {
        return experienceRepository.findByYearAndParentTagId(year, parentTagId)
    }

    fun readByUserIdAndParentTagId(userId: UUID, parentTagId: UUID): List<Experience> {
        return experienceRepository.findByUserIdAndParentTagId(userId, parentTagId)
    }

    fun readByUserIdAndParentTagIdAndYearDesc(year: Int, parentTagId: UUID, userId: UUID): List<Experience> {
        return experienceRepository.findByUserIdAndParentTagIdAndYearDesc(year, parentTagId, userId)
    }

    fun readByChildTagIdAndYear(year: Int, childTagId: UUID): List<Experience> {
        return experienceRepository.findByYearAndChildTagId(year, childTagId)
    }
}
