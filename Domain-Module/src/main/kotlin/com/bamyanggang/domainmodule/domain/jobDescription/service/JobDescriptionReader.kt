package com.bamyanggang.domainmodule.domain.jobDescription.service

import com.bamyanggang.domainmodule.common.pagination.PageDomain
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.JobDescription
import com.bamyanggang.domainmodule.domain.jobDescription.enums.SortType
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import com.bamyanggang.domainmodule.domain.jobDescription.repository.JobDescriptionRepository
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

class JobDescriptionReader(
    private val jobDescriptionRepository: JobDescriptionRepository
) {
    fun readJobDescriptionByUserIdAndSortType(userId: UUID, page: Int, size: Int, sortType: SortType?, writeStatus: WriteStatus?): PageDomain<JobDescription> {
        return when(sortType) {
            SortType.CREATED -> jobDescriptionRepository.findAllByUserIdAndSortByCreatedAt(userId, page, size, writeStatus)
            SortType.ENDED -> sortWithEndedAt(jobDescriptionRepository.findAllByUserId(userId, page, size, writeStatus))
            else -> jobDescriptionRepository.findAllByUserId(userId, page, size, writeStatus)
        }
    }

    private fun sortWithEndedAt (pageDomain: PageDomain<JobDescription>): PageDomain<JobDescription> {
        val now = LocalDateTime.now()
        val futureList = pageDomain.content.filter { it.endedAt.isAfter(now) }
        val pastList = pageDomain.content.filter { it.endedAt.isBefore(now) }

        val sortedFutureList = futureList.sortedWith(compareBy { kotlin.math.abs(ChronoUnit.DAYS.between(it.endedAt, now)) })
        val sortedPastList = pastList.sortedWith(compareByDescending { it.endedAt })

        val sortedList = sortedFutureList + sortedPastList

        return PageDomain(sortedList, pageDomain.pageNumber, pageDomain.pageSize, pageDomain.totalPage, pageDomain.hasNext)
    }

    fun readJobDescriptionById(jobDescriptionId: UUID): JobDescription {
        return jobDescriptionRepository.findById(jobDescriptionId)
    }

}
