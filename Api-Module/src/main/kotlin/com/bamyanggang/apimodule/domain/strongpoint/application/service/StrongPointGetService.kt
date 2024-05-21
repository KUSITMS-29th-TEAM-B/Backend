package com.bamyanggang.apimodule.domain.strongpoint.application.service

import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.apimodule.domain.strongpoint.application.dto.GetStrongPoint
import com.bamyanggang.domainmodule.domain.strongpoint.service.StrongPointReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StrongPointGetService(
    private val strongPointReader: StrongPointReader,
) {
    @Transactional(readOnly = true)
    fun getAllStrongPoints(): GetStrongPoint.Response {
        return getAuthenticationPrincipal().let {
            val detailStrongPoints = strongPointReader.readAllByUserId(it).map { strongPoint ->
                GetStrongPoint.DetailStrongPoint(strongPoint.id, strongPoint.name)
            }

            GetStrongPoint.Response(detailStrongPoints.size, detailStrongPoints)
        }
    }
}
