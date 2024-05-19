package com.bamyanggang.apimodule.domain.jobDescription.application.service

import com.bamyanggang.apimodule.common.dto.SliceResponse
import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.apimodule.domain.jobDescription.application.dto.GetJobDescriptionInfo
import com.bamyanggang.domainmodule.common.pagination.SliceDomain
import com.bamyanggang.domainmodule.domain.jobDescription.enums.SortType
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import com.bamyanggang.domainmodule.domain.jobDescription.service.ApplyReader
import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionReader
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.SliceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class JobDescriptionInfoGetService(
    private val jobDescriptionReader: JobDescriptionReader,
    private val applyReader: ApplyReader
) {

    @Transactional(readOnly = true)
    fun getJobDescriptionInfo(pageable: Pageable, writeStatus: WriteStatus?, sortType: SortType?): SliceResponse<GetJobDescriptionInfo.Response> {
        return getAuthenticationPrincipal().let{ userId ->
            val jobDescriptions = jobDescriptionReader.readJobDescriptionByUserIdAndSortType(userId, pageable.pageNumber, pageable.pageSize, sortType)

            val jobDescriptionInfoResponses = jobDescriptions.content.map{ jobDescription ->
                val apply = applyReader.readApplyByJobDescriptionId(jobDescription.id)
                GetJobDescriptionInfo.Response(
                    jobDescription.id,
                    jobDescription.getRemainingDate(),
                    jobDescription.enterpriseName,
                    jobDescription.title,
                    apply?.writeStatus?: WriteStatus.NOT_APPLIED,
                    jobDescription.createdAt,
                    jobDescription.startedAt,
                    jobDescription.endedAt
                )
            }

            val jobDescriptionsSlice = SliceDomain(jobDescriptionInfoResponses, jobDescriptions.pageNumber, jobDescriptions.pageSize, jobDescriptions.hasNext)
            SliceResponse.from(jobDescriptionsSlice)
        }
    }


}
