package com.bamyanggang.domainmodule.domain.strongpoint.service

import com.bamyanggang.domainmodule.domain.experience.repository.StrongPointRepository
import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.StrongPoint
import org.springframework.stereotype.Service
import java.util.*

@Service
class StrongPointAppender(
    private val strongPointRepository: StrongPointRepository
) {
    fun appendStrongPoint(name: String, userId: UUID): UUID {
        val newStrongPoint = StrongPoint.create(name, userId)
        val newStrongPointId = strongPointRepository.save(newStrongPoint)

        return newStrongPointId
    }
}