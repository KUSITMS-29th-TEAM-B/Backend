package com.bamyanggang.apimodule.domain.experience.presentation

import com.bamyanggang.apimodule.domain.experience.application.dto.CreateExperience
import com.bamyanggang.apimodule.domain.experience.application.service.ExperienceCreateService
import com.bamyanggang.apimodule.domain.experience.application.service.ExperienceDeleteService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class ExperienceController(
    private val experienceCreateService: ExperienceCreateService,
    private val experienceDeleteService: ExperienceDeleteService
) {
    @PostMapping(ExperienceApi.BASE_URL)
    fun createExperience(@RequestBody request: CreateExperience.Request): CreateExperience.Response {
        return experienceCreateService.createExperience(request)
    }

    @DeleteMapping(ExperienceApi.EXPERIENCE_PATH_VARIABLE_URL)
    fun deleteExperience(@PathVariable("experienceId") experienceId: UUID) {
        experienceDeleteService.deleteExperienceById(experienceId)
    }
}
