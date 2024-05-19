package com.bamyanggang.domainmodule.domain.jobDescription.service

import com.bamyanggang.domainmodule.common.pagination.PageDomain
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.JobDescription
import com.bamyanggang.domainmodule.domain.jobDescription.enums.SortType
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import com.bamyanggang.domainmodule.domain.jobDescription.repository.JobDescriptionRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class JobDescriptionReader(
    private val jobDescriptionRepository: JobDescriptionRepository
) {
    fun readJobDescriptionByUserIdAndSortType(userId: UUID, page: Int, size: Int, sortType: SortType?, writeStatus: WriteStatus?): PageDomain<JobDescription> {
        return when(sortType) {
            SortType.CREATED -> jobDescriptionRepository.findAllByUserIdAndSortByCreatedAt(userId, page, size, writeStatus)
            SortType.ENDED -> jobDescriptionRepository.findAllByUserIdAndSortByEndedAt(userId, page, size, writeStatus)
            else -> jobDescriptionRepository.findAllByUserId(userId, page, size, writeStatus)
        }
    }

    fun readJobDescriptionById(jobDescriptionId: UUID): JobDescription {
        return jobDescriptionRepository.findById(jobDescriptionId)
    }

}
