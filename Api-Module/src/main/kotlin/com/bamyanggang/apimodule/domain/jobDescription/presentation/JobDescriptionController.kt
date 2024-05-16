package com.bamyanggang.apimodule.domain.jobDescription.presentation

import com.bamyanggang.apimodule.domain.jobDescription.application.dto.CreateApply
import com.bamyanggang.apimodule.domain.jobDescription.application.dto.CreateJobDescription
import com.bamyanggang.apimodule.domain.jobDescription.application.service.JobDescriptionCreateService
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class JobDescriptionController(
    private val jobDescriptionCreateService: JobDescriptionCreateService,
) {

    @PostMapping(JobDescriptionApi.BASE_URL)
    fun createJobDescription(
        @RequestBody request: CreateJobDescription.Request
    ):  CreateJobDescription.Response = jobDescriptionCreateService.createJobDescription(request)
}
