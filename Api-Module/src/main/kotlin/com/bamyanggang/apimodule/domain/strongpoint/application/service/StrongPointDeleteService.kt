package com.bamyanggang.apimodule.domain.strongpoint.application.service

import ch.qos.logback.core.rolling.helper.ArchiveRemover
import com.bamyanggang.domainmodule.domain.strongpoint.service.StrongPointRemover
import org.springframework.stereotype.Service
import java.util.*

@Service
class StrongPointDeleteService(
    private val strongPointRemover: StrongPointRemover
) {
    fun deleteStrongPoint(strongPointId: UUID) {
        strongPointRemover.removeStrongPoint(strongPointId)
    }
}
