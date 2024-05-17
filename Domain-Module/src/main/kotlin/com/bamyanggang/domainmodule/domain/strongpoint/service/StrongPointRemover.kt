package com.bamyanggang.domainmodule.domain.strongpoint.service

import com.bamyanggang.domainmodule.domain.experience.repository.StrongPointRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class StrongPointRemover(
    private val strongPointRepository: StrongPointRepository
) {
    fun removeStrongPoint(strongPointId: UUID) = strongPointRepository.deleteByStrongPointId(strongPointId)
}
