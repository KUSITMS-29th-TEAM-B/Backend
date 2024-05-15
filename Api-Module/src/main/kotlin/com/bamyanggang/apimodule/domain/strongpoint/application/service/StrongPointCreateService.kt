package com.bamyanggang.apimodule.domain.strongpoint.application.service

import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.apimodule.domain.strongpoint.application.dto.CreateStrongPoint
import com.bamyanggang.apimodule.domain.strongpoint.application.exception.StrongPointException
import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.StrongPoint
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
                val userStrongPoints = strongPointReader.findAllByUserId(it)
                validateDuplicatedName(userStrongPoints, request.name)
            }.let {
                val newStrongPointId = strongPointAppender.appendStrongPoint(request.name, it)
                CreateStrongPoint.Response(newStrongPointId)
            }
    }

    private fun validateDuplicatedName(userStrongPoints: List<StrongPoint>, name: String) {
        userStrongPoints.forEach {
            if (name == it.name) {
                throw StrongPointException.DuplicatedStrongPointName()
            }
        }
    }
}