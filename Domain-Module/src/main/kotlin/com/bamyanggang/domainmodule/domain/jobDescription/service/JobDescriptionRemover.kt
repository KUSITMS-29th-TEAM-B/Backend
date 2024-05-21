package com.bamyanggang.domainmodule.domain.jobDescription.service

import com.bamyanggang.domainmodule.domain.jobDescription.repository.JobDescriptionRepository
import java.util.*

class JobDescriptionRemover(
    private val jobDescriptionRepository: JobDescriptionRepository
) {

    fun removeJobDescription(jobDescriptionId: UUID) {
        jobDescriptionRepository.deleteById(jobDescriptionId)
    }

}
