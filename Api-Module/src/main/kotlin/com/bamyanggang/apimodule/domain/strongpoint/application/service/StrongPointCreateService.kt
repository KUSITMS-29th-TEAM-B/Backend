package com.bamyanggang.apimodule.domain.strongpoint.application.service

import com.bamyanggang.apimodule.domain.strongpoint.application.dto.CreateStrongPoint
import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.StrongPoint
import com.bamyanggang.domainmodule.domain.strongpoint.exception.StrongPointException
import com.bamyanggang.domainmodule.domain.strongpoint.service.StrongPointAppender
import com.bamyanggang.domainmodule.domain.strongpoint.service.StrongPointReader
import org.springframework.stereotype.Service
import java.util.*

@Service
class StrongPointCreateService(
    val strongPointAppender: StrongPointAppender,
    val strongPointReader: StrongPointReader,
) {
    fun createStrongPoint(request: CreateStrongPoint.Request, userId: UUID): CreateStrongPoint.Response {
        val userStrongPoints = strongPointReader.readAllByUserId(userId)

        validateOverCountLimit(userStrongPoints)
        validateDuplicatedName(userStrongPoints, request)

        val newStrongPointId = strongPointAppender.appendStrongPoint(request.name, userId)

        return CreateStrongPoint.Response(newStrongPointId)
    }

    private fun validateOverCountLimit(userStrongPoints: List<StrongPoint>) {
        if (userStrongPoints.size >= StrongPoint.LIMIT) {
            throw StrongPointException.OverCountLimit()
        }
    }

    private fun validateDuplicatedName(
        userStrongPoints: List<StrongPoint>,
        request: CreateStrongPoint.Request,
    ) {
        userStrongPoints.forEach { strongPoint ->
            if (strongPoint.isDuplicated(request.name))
                throw StrongPointException.DuplicatedName()
        }
    }
}
