package com.bamyanggang.apimodule.domain.strongpoint.application.service

import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.apimodule.domain.strongpoint.application.dto.GetStrongPoint
import com.bamyanggang.domainmodule.domain.strongpoint.service.StrongPointReader
import org.springframework.stereotype.Service

@Service
class StrongPointGetService(
    private val strongPointReader: StrongPointReader,
) {
    fun getAllStrongPoints(): GetStrongPoint.Response {
        return getAuthenticationPrincipal().let {
            val strongPointInfos = strongPointReader.readAllByUserId(it).map { strongPoint ->
                GetStrongPoint.StrongPointInfo(strongPoint.id, strongPoint.name)
            }

            GetStrongPoint.Response(strongPointInfos.size, strongPointInfos)
        }
    }
}
