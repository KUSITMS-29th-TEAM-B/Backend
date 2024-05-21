package com.bamyanggang.domainmodule.domain.strongpoint.service

import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.StrongPoint
import com.bamyanggang.domainmodule.domain.strongpoint.repository.StrongPointRepository
import java.util.*

class StrongPointAppender(
    private val strongPointRepository: StrongPointRepository
) {
    fun appendStrongPoint(name: String, userId: UUID): StrongPoint {
        return StrongPoint.create(name, userId).also {
            strongPointRepository.save(it)
        }
    }

    fun appendAllStrongPoint(names: List<String>, userId: UUID): List<StrongPoint> {
        val newStrongPoints = names.map { name ->
            StrongPoint.create(name, userId)
        }

        strongPointRepository.saveAll(newStrongPoints)
        return newStrongPoints
    }
}
