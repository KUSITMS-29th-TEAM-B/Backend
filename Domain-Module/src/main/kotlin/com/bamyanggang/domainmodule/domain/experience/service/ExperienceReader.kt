package com.bamyanggang.domainmodule.domain.experience.service

import com.bamyanggang.domainmodule.domain.experience.aggregate.Experience
import com.bamyanggang.domainmodule.domain.experience.repository.ExperienceRepository
import java.util.*

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

    fun readByUserIdAndYearDesc(year: Int, userId: UUID): List<Experience> {
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

    fun readByIds(experienceIds: List<UUID>) : List<Experience> {
        return experienceRepository.findByIds(experienceIds)
    }

    fun readIdsByUserIdAndTitleContains(userId: UUID, search: String): List<UUID> {
        return experienceRepository.findByUserIdAndTitleContains(userId, search).map { it.id }
    }

    fun readIdsByContentsContains(userId: UUID, search: String): List<UUID> {
        val experiences = experienceRepository.findAllByUserId(userId)

        return experiences.filter {
            it.contents.map { content ->
                content.question.contains(search) || content.answer.contains(search)
            }.contains(true)
        }.map { it.id }
    }
    fun readByYear(year: Int): List<Experience> {
        return experienceRepository.findByYear(year)
    }

    fun readByChildTag(childTag: UUID): List<Experience> {
        return experienceRepository.findByChildTagId(childTag)
    }

    fun readIdsByTagIds(tagIds: List<UUID>) : List<UUID> {
        return experienceRepository.findByTagIds(tagIds).map { it.id }
    }

    fun readIdsByStrongPointIds(userId: UUID, strongPointIds: List<UUID>) : List<UUID> {
        val experiences = experienceRepository.findAllByUserId(userId)
        val filteredExperience: MutableList<Experience> = mutableListOf()

        strongPointIds.forEach { strongPointId ->
            val fragExperiences = experiences.filter { experience ->
                experience.strongPoints.map {
                    it.strongPointId
                }.contains(strongPointId)
            }

            filteredExperience.addAll(fragExperiences)
        }

        return filteredExperience.map { it.id }
    }
}
