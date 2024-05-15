package com.bamyanggang.apimodule.domain.experience.application.service

import com.bamyanggang.apimodule.common.SecurityUtils
import com.bamyanggang.apimodule.domain.experience.application.dto.CreateStrongPointRequest
import com.bamyanggang.apimodule.domain.experience.application.dto.CreateStrongPointResponse
import com.bamyanggang.domainmodule.domain.strongpoint.service.StrongPointAppender
import org.springframework.stereotype.Service

@Service
class StrongPointCreateService(
    val strongPointAppender: StrongPointAppender,
) {
    fun createStrongPoint(request: CreateStrongPointRequest): CreateStrongPointResponse {
        val accessUserId = SecurityUtils.getAuthenticationPrincipal()

        val strongPointId = strongPointAppender.appendStrongPoint(request.name, accessUserId)

        return CreateStrongPointResponse(strongPointId)
    }
}