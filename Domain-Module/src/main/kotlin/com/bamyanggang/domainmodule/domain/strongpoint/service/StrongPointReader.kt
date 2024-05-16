package com.bamyanggang.domainmodule.domain.strongpoint.service

import com.bamyanggang.domainmodule.domain.strongpoint.repository.StrongPointRepository
import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.StrongPoint
import org.springframework.stereotype.Service
import java.util.*

@Service
class StrongPointReader(
    private val strongPointRepository: StrongPointRepository
) {
    fun findAllByUserId(userId: UUID): List<StrongPoint> {
        return strongPointRepository.findAllByUserId(userId)
    }
}
