package com.bamyanggang.domainmodule.domain.jobDescription.repository

import com.bamyanggang.domainmodule.common.pagination.SliceDomain
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.JobDescription
import java.util.*

interface JobDescriptionRepository {

    fun save(jobDescription: JobDescription)

    fun findById(jobDescriptionId: UUID): JobDescription

    fun findAllByUserIdAndSortByCreatedAt(userId: UUID, page: Int, size: Int): SliceDomain<JobDescription>

    fun findAllByUserId(userId: UUID, page: Int, size: Int): SliceDomain<JobDescription>

}
