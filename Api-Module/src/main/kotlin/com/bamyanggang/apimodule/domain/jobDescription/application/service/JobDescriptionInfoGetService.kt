package com.bamyanggang.apimodule.domain.jobDescription.application.service

import com.bamyanggang.apimodule.common.dto.PageResponse
import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.apimodule.domain.jobDescription.application.dto.GetJobDescriptionInfo
import com.bamyanggang.domainmodule.common.pagination.PageDomain
import com.bamyanggang.domainmodule.domain.jobDescription.enums.SortType
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionReader
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class JobDescriptionInfoGetService(
    private val jobDescriptionReader: JobDescriptionReader,
) {

    @Transactional(readOnly = true)
    fun getJobDescriptionInfo(pageable: Pageable, writeStatus: WriteStatus?, sortType: SortType?): PageResponse<GetJobDescriptionInfo.Response> {
        return getAuthenticationPrincipal().let{ userId ->
            val jobDescriptions = jobDescriptionReader.readJobDescriptionByUserIdAndSortType(userId, pageable.pageNumber, pageable.pageSize, sortType, writeStatus)

            val jobDescriptionInfoResponses = jobDescriptions.content.map{ jobDescription ->
                GetJobDescriptionInfo.Response(
                    jobDescription.id,
                    jobDescription.getRemainingDate(),
                    jobDescription.enterpriseName,
                    jobDescription.title,
                    jobDescription.writeStatus,
                    jobDescription.createdAt,
                    jobDescription.startedAt,
                    jobDescription.endedAt
                )
            }

            val jobDescriptionsSlice = PageDomain(jobDescriptionInfoResponses, jobDescriptions.pageNumber, jobDescriptions.pageSize, jobDescriptions.totalPage,
                jobDescriptions.hasNext)
            PageResponse.from(jobDescriptionsSlice)
        }
    }


}
