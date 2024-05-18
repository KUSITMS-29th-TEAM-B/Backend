package com.bamyanggang.domainmodule.domain.strongpoint.service

import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.StrongPoint
import com.bamyanggang.domainmodule.domain.strongpoint.repository.StrongPointRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class StrongPointAppender(
    private val strongPointRepository: StrongPointRepository
) {
    fun appendStrongPoint(name: String, userId: UUID): StrongPoint {
        return StrongPoint.create(name, userId).also {
            strongPointRepository.save(it)
        }
    }
}
