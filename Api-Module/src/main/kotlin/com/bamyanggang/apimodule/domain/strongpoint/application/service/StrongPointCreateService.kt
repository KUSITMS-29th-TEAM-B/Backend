package com.bamyanggang.apimodule.domain.strongpoint.application.service

import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.apimodule.domain.strongpoint.application.dto.CreateStrongPoint
import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.StrongPoint
import com.bamyanggang.domainmodule.domain.strongpoint.exception.StrongPointException
import com.bamyanggang.domainmodule.domain.strongpoint.exception.StrongPointExceptionMessage
import com.bamyanggang.domainmodule.domain.strongpoint.service.StrongPointAppender
import com.bamyanggang.domainmodule.domain.strongpoint.service.StrongPointReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StrongPointCreateService(
    val strongPointAppender: StrongPointAppender,
    val strongPointReader: StrongPointReader,
) {
    @Transactional
    fun createStrongPoint(request: CreateStrongPoint.Request): CreateStrongPoint.Response {
        val currentUserId = getAuthenticationPrincipal()
        val newStrongPointNames = request.names.map { it.name }
        val currentUserStrongPoints = strongPointReader.readAllByUserId(currentUserId)

        validateOverCountLimit(currentUserStrongPoints, newStrongPointNames.size)

        newStrongPointNames.forEach {
            validateDuplicatedName(currentUserStrongPoints, it)
        }

        val newStrongPoints = strongPointAppender.appendAllStrongPoint(newStrongPointNames, currentUserId).map {
            CreateStrongPoint.DetailStrongPoint(it.id, it.name)
        }

        return CreateStrongPoint.Response(newStrongPoints)
    }

    private fun validateOverCountLimit(userStrongPoints: List<StrongPoint>, count: Int) {
        if (userStrongPoints.size >= StrongPoint.LIMIT || (userStrongPoints.size + count) > StrongPoint.LIMIT) {
            throw StrongPointException.OverCountLimit()
        }
    }

    private fun validateDuplicatedName(
        userStrongPoints: List<StrongPoint>,
        name: String,
    ) {
        userStrongPoints.forEach { strongPoint ->
            if (strongPoint.isDuplicated(name))
                throw IllegalArgumentException(StrongPointExceptionMessage.DUPLICATED_NAME.message)
        }
    }
}


