package com.bamyanggang.apimodule.domain.strongpoint.application.service

import com.bamyanggang.apimodule.domain.strongpoint.application.dto.CreateStrongPoint
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
        strongPointReader.readAllByUserId(userId).forEach { strongPoint ->
            if(strongPoint.isDuplicated(request.name))
                throw StrongPointException.DuplicatedName()
        }
        val newStrongPointId = strongPointAppender.appendStrongPoint(request.name, userId)

        return CreateStrongPoint.Response(newStrongPointId)
    }
}
