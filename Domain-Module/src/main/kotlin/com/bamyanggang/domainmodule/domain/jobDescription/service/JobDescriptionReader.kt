package com.bamyanggang.domainmodule.domain.jobDescription.service

import com.bamyanggang.domainmodule.common.pagination.SliceDomain
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.JobDescription
import com.bamyanggang.domainmodule.domain.jobDescription.enums.SortType
import com.bamyanggang.domainmodule.domain.jobDescription.repository.JobDescriptionRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class JobDescriptionReader(
    private val jobDescriptionRepository: JobDescriptionRepository
) {
    /**
     * TODO: 마감순 필터링 추가
     */
    fun readJobDescriptionByUserIdAndSortType(userId: UUID, page: Int, size: Int, sortType: SortType?): SliceDomain<JobDescription> {
        return when(sortType) {
            SortType.CREATED -> jobDescriptionRepository.findAllByUserIdAndSortByCreatedAt(userId, page, size)
//            SortType.ENDED -> jobDescriptionRepository.findAllByUserIdWithApply(userId, pageable)
            else -> jobDescriptionRepository.findAllByUserId(userId, page, size)
        }
    }


}
