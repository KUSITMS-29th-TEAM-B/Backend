package com.bamyanggang.apimodule.domain.strongpoint.application.service

import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.apimodule.domain.strongpoint.application.dto.CreateStrongPoint
import com.bamyanggang.domainmodule.domain.strongpoint.exception.StrongPointException
import com.bamyanggang.domainmodule.domain.strongpoint.service.StrongPointAppender
import com.bamyanggang.domainmodule.domain.strongpoint.service.StrongPointReader
import org.springframework.stereotype.Service

@Service
class StrongPointCreateService(
    val strongPointAppender: StrongPointAppender,
    val strongPointReader: StrongPointReader,
) {
    fun createStrongPoint(request: CreateStrongPoint.Request): CreateStrongPoint.Response {
        return getAuthenticationPrincipal()
            .also {
                strongPointReader.readAllByUserId(it).forEach { strongPoint ->
                    if(strongPoint.isDuplicated(request.name))
                        throw StrongPointException.DuplicatedStrongPointName()
                }
            }.let {
                val newStrongPointId = strongPointAppender.appendStrongPoint(request.name, it)
                CreateStrongPoint.Response(newStrongPointId)
            }
    }
}
