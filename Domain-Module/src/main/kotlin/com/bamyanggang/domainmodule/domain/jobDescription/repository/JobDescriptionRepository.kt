package com.bamyanggang.domainmodule.domain.jobDescription.repository

import com.bamyanggang.domainmodule.common.pagination.PageDomain
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.JobDescription
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import java.util.*

interface JobDescriptionRepository {

    fun save(jobDescription: JobDescription)

    fun findById(jobDescriptionId: UUID): JobDescription

    fun findAllByUserIdAndSortByCreatedAt(userId: UUID, page: Int, size: Int, writeStatus: WriteStatus?): PageDomain<JobDescription>

    fun findAllByUserIdAndSortByEndedAt(userId: UUID, page: Int, size: Int, writeStatus: WriteStatus?): PageDomain<JobDescription>

    fun findAllByUserId(userId: UUID, page: Int, size: Int, writeStatus: WriteStatus?): PageDomain<JobDescription>

}
