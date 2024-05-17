package com.bamyanggang.apimodule.domain.experience.presentation

import com.bamyanggang.apimodule.domain.experience.application.dto.CreateExperience
import com.bamyanggang.apimodule.domain.experience.application.service.ExperienceCreateService
import com.bamyanggang.apimodule.domain.tag.presentation.TagApi
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ExperienceController(
    private val experienceCreateService: ExperienceCreateService
) {

    @PostMapping(TagApi.BASE_URL)
    fun createExperience(@RequestBody request: CreateExperience.Request): CreateExperience.Response {

    }
}
