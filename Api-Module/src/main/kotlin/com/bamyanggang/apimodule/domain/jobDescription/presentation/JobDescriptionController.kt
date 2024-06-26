package com.bamyanggang.apimodule.domain.jobDescription.presentation

import com.bamyanggang.apimodule.common.dto.PageResponse
import com.bamyanggang.apimodule.domain.jobDescription.application.dto.*
import com.bamyanggang.apimodule.domain.jobDescription.application.service.*
import com.bamyanggang.domainmodule.domain.jobDescription.enums.SortType
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class JobDescriptionController(
    private val jobDescriptionCreateService: JobDescriptionCreateService,
    private val applyCreateService: ApplyCreateService,
    private val jobDescriptionInfoGetService: JobDescriptionInfoGetService,
    private val applyInfoGetService: ApplyInfoGetService,
    private val applyUpdateService: ApplyUpdateService,
    private val jobDescriptionInfoUpdateService: JobDescriptionInfoUpdateService,
    private val jobDescriptionDeleteService: JobDescriptionDeleteService
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
    ): PageResponse<JobDescriptionInfo.Response.Basic> {
        return jobDescriptionInfoGetService.getJobDescriptionInfo(pageable, writeStatus, sortType)
    }

    @GetMapping(JobDescriptionApi.DETAIL)
    fun getJobDescriptionDetail(
        @PathVariable("jobDescriptionId") jobDescriptionId: UUID
    ): JobDescriptionInfo.Response.Detail = jobDescriptionInfoGetService.getJobDescriptionDetail(jobDescriptionId)

    @GetMapping(JobDescriptionApi.APPLY)
    fun getApplyInfo(
        @PathVariable("jobDescriptionId") jobDescriptionId: UUID
    ): ApplyInfo.Response = applyInfoGetService.getApplyInfo(jobDescriptionId)

   @PutMapping(JobDescriptionApi.APPLY)
    fun updateApplyInfo(
        @PathVariable("jobDescriptionId") jobDescriptionId: UUID,
        @RequestBody request: ApplyInfo.Request.Update
    ) = applyUpdateService.updateApplyInfo(jobDescriptionId, request)

    @PatchMapping(JobDescriptionApi.STATUS)
    fun updateJobDescriptionWriteStatus(
        @PathVariable("jobDescriptionId") jobDescriptionId: UUID
    ) = jobDescriptionInfoUpdateService.updateWriteStatus(jobDescriptionId)

    @PatchMapping(JobDescriptionApi.DETAIL)
    fun updateJobDescriptionDetail(
        @PathVariable("jobDescriptionId") jobDescriptionId: UUID,
        @RequestBody request: JobDescriptionInfo.Request.Update
    ) = jobDescriptionInfoUpdateService.updateJobDescriptionDetail(jobDescriptionId, request)

    @DeleteMapping(JobDescriptionApi.DETAIL)
    fun deleteJobDescription(
        @PathVariable("jobDescriptionId") jobDescriptionId: UUID
    ) = jobDescriptionDeleteService.deleteJobDescription(jobDescriptionId)

}
