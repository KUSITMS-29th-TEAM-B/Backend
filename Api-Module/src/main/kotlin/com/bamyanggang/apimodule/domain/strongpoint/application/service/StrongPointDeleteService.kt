package com.bamyanggang.apimodule.domain.strongpoint.application.service

import com.bamyanggang.domainmodule.domain.strongpoint.service.StrongPointRemover
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class StrongPointDeleteService(
    private val strongPointRemover: StrongPointRemover
) {
    @Transactional
    fun deleteStrongPoint(strongPointId: UUID) {
        strongPointRemover.removeStrongPoint(strongPointId)
    }
}
