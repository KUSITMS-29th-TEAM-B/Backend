package com.bamyanggang.domainmodule.domain.strongpoint.service

import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.StrongPoint
import com.bamyanggang.domainmodule.domain.strongpoint.repository.StrongPointRepository
import java.util.*

class StrongPointReader(
    private val strongPointRepository: StrongPointRepository,
    private val keywordReader : KeywordReader
) {
    fun readAllByUserId(userId: UUID): List<StrongPoint> {
        return strongPointRepository.findAllByUserId(userId)
    }

    fun readByIds(strongPointIds: List<UUID>) : List<StrongPoint> {
        val defaultStrongPointIds = keywordReader.readByIds(strongPointIds).map { it.defaultKeywordId }
        val defaultStrongPoints = strongPointRepository.findByIds(defaultStrongPointIds)
        val restStrongPointIds = strongPointIds.filter { !defaultStrongPointIds.contains(it) }

        return strongPointRepository.findByIds(restStrongPointIds) + defaultStrongPoints
    }

    fun readIdsByUserIdAndNameContains(userId: UUID, search: String) : List<UUID> {
        return strongPointRepository.findByUserIdAndNameContains(userId, search).map { it.id }
    }
}
