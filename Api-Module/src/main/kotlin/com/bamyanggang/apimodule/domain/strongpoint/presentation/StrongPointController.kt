package com.bamyanggang.apimodule.domain.strongpoint.presentation

import com.bamyanggang.apimodule.domain.strongpoint.application.dto.CreateStrongPoint
import com.bamyanggang.apimodule.domain.strongpoint.application.service.StrongPointCreateService
import com.bamyanggang.apimodule.domain.strongpoint.application.service.StrongPointDeleteService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class StrongPointController(
    private val strongPointCreateService: StrongPointCreateService,
    private val strongPointDeleteService: StrongPointDeleteService
) {
    @PostMapping(StrongPointApi.BASE_URL)
    fun createStrongPoint(@RequestBody request: CreateStrongPoint.Request): CreateStrongPoint.Response {
        return strongPointCreateService.createStrongPoint(request)
    }

    @DeleteMapping(StrongPointApi.STRONG_POINT_PATH_VARIABLE_URL)
    fun deleteStrongPoint(@PathVariable("strongPointId") strongPointId: UUID) {
        strongPointDeleteService.deleteStrongPoint(strongPointId)
    }
}
