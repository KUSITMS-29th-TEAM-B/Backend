package com.bamyanggang.apimodule.domain.jobDescription.application.service

import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionRemover
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class JobDescriptionDeleteService(
    private val jobDescriptionRemover: JobDescriptionRemover
) {

    @Transactional
    fun deleteJobDescription(jobDescriptionId: UUID) {
        jobDescriptionRemover.removeJobDescription(jobDescriptionId)
    }

}
