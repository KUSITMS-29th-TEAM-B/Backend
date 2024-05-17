package com.bamyanggang.apimodule.domain.jobDescription.presentation

import com.bamyanggang.apimodule.domain.jobDescription.application.dto.CreateApply
import com.bamyanggang.apimodule.domain.jobDescription.application.dto.CreateJobDescription
import com.bamyanggang.apimodule.domain.jobDescription.application.service.ApplyCreateService
import com.bamyanggang.apimodule.domain.jobDescription.application.service.JobDescriptionCreateService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class JobDescriptionController(
    private val jobDescriptionCreateService: JobDescriptionCreateService,
    private val applyCreateService: ApplyCreateService
) {

    @PostMapping(JobDescriptionApi.BASE_URL)
    fun createJobDescription(
        @RequestBody request: CreateJobDescription.Request
    ):  CreateJobDescription.Response = jobDescriptionCreateService.createJobDescription(request)

    @PostMapping(JobDescriptionApi.APPLY)
    fun createApply(
        @PathVariable("jobDescriptionId") jobDescriptionId: UUID,
        @RequestBody request: CreateApply.Request
    ) = applyCreateService.createApply(request, jobDescriptionId)

}
