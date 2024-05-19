package com.bamyanggang.apimodule.domain.jobDescription.presentation

import com.bamyanggang.apimodule.common.dto.PageResponse
import com.bamyanggang.apimodule.domain.jobDescription.application.dto.CreateApply
import com.bamyanggang.apimodule.domain.jobDescription.application.dto.CreateJobDescription
import com.bamyanggang.apimodule.domain.jobDescription.application.dto.GetApplyInfo
import com.bamyanggang.apimodule.domain.jobDescription.application.dto.GetJobDescriptionInfo
import com.bamyanggang.apimodule.domain.jobDescription.application.service.ApplyCreateService
import com.bamyanggang.apimodule.domain.jobDescription.application.service.ApplyInfoGetService
import com.bamyanggang.apimodule.domain.jobDescription.application.service.JobDescriptionCreateService
import com.bamyanggang.apimodule.domain.jobDescription.application.service.JobDescriptionInfoGetService
import com.bamyanggang.domainmodule.domain.jobDescription.enums.SortType
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class JobDescriptionController(
    private val jobDescriptionCreateService: JobDescriptionCreateService,
    private val applyCreateService: ApplyCreateService,
    private val jobDescriptionInfoGetService: JobDescriptionInfoGetService,
    private val applyInfoGetService: ApplyInfoGetService
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

    @GetMapping(JobDescriptionApi.BASE_URL)
    fun getJobDescription(
        pageable: Pageable,
        @RequestParam writeStatus: WriteStatus?,
        @RequestParam sortType: SortType?
    ): PageResponse<GetJobDescriptionInfo.Response.Basic> {
        return jobDescriptionInfoGetService.getJobDescriptionInfo(pageable, writeStatus, sortType)
    }

    @GetMapping(JobDescriptionApi.DETAIL)
    fun getJobDescriptionDetail(
        @PathVariable("jobDescriptionId") jobDescriptionId: UUID
    ): GetJobDescriptionInfo.Response.Detail = jobDescriptionInfoGetService.getJobDescriptionDetail(jobDescriptionId)

    @GetMapping(JobDescriptionApi.APPLY)
    fun getApplyInfo(
        @PathVariable("jobDescriptionId") jobDescriptionId: UUID
    ): GetApplyInfo.Response = applyInfoGetService.getApplyInfo(jobDescriptionId)

}
