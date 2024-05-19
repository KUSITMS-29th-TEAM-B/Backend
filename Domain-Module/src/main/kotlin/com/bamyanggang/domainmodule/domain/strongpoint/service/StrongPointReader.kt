package com.bamyanggang.domainmodule.domain.strongpoint.service

import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.StrongPoint
import com.bamyanggang.domainmodule.domain.strongpoint.repository.StrongPointRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class StrongPointReader(
    private val strongPointRepository: StrongPointRepository
) {
    fun readAllByUserId(userId: UUID): List<StrongPoint> {
        return strongPointRepository.findAllByUserId(userId)
    }

    fun readByIds(strongPointIds: List<UUID>) : List<StrongPoint> {
        return strongPointRepository.findByIds(strongPointIds)
    }
}
