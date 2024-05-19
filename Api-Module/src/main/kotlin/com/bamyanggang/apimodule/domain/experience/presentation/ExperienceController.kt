package com.bamyanggang.apimodule.domain.experience.presentation

import com.bamyanggang.apimodule.domain.experience.application.dto.CreateExperience
import com.bamyanggang.apimodule.domain.experience.application.dto.DetailExperience
import com.bamyanggang.apimodule.domain.experience.application.dto.EditExperience
import com.bamyanggang.apimodule.domain.experience.application.dto.ExperienceYear
import com.bamyanggang.apimodule.domain.experience.application.service.ExperienceCreateService
import com.bamyanggang.apimodule.domain.experience.application.service.ExperienceDeleteService
import com.bamyanggang.apimodule.domain.experience.application.service.ExperienceEditService
import com.bamyanggang.apimodule.domain.experience.application.service.ExperienceGetService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class ExperienceController(
    private val experienceCreateService: ExperienceCreateService,
    private val experienceDeleteService: ExperienceDeleteService,
    private val experienceEditService: ExperienceEditService,
    private val experienceGetService: ExperienceGetService
) {
    @GetMapping(ExperienceApi.EXPERIENCE_PATH_VARIABLE_URL)
    fun getExperience(@PathVariable("experienceId") experienceId: UUID): DetailExperience.Response {
        return experienceGetService.getExperienceDetailById(experienceId)
    }
    
    @GetMapping(ExperienceApi.ALL_YEARS)
    fun getAllYearsByExistExperience() : ExperienceYear.Response {
        return experienceGetService.getAllYearsByExistExperience()
    }

    @PostMapping(ExperienceApi.BASE_URL)
    fun createExperience(@RequestBody request: CreateExperience.Request): CreateExperience.Response {
        return experienceCreateService.createExperience(request)
    }

    @DeleteMapping(ExperienceApi.EXPERIENCE_PATH_VARIABLE_URL)
    fun deleteExperience(@PathVariable("experienceId") experienceId: UUID) {
        experienceDeleteService.deleteExperienceById(experienceId)
    }

    @PatchMapping(ExperienceApi.EXPERIENCE_PATH_VARIABLE_URL)
    fun editExperience(@RequestBody request: EditExperience.Request,
                       @PathVariable experienceId: UUID): EditExperience.Response {
        return experienceEditService.editExperienceById(request, experienceId)
    }
}
