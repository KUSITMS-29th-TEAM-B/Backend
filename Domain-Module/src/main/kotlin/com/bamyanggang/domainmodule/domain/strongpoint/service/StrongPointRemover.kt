package com.bamyanggang.domainmodule.domain.strongpoint.service

import com.bamyanggang.domainmodule.domain.strongpoint.exception.StrongPointExceptionMessage
import com.bamyanggang.domainmodule.domain.strongpoint.repository.StrongPointRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class StrongPointRemover(
    private val strongPointRepository: StrongPointRepository
) {
    fun removeStrongPoint(strongPointId: UUID) {
        if (!strongPointRepository.isExistByStrongPointId(strongPointId)) {
            throw IllegalArgumentException(StrongPointExceptionMessage.NOT_FOUND_STRONG_POINT.message)
        }

        strongPointRepository.deleteByStrongPointId(strongPointId)
    }
}
