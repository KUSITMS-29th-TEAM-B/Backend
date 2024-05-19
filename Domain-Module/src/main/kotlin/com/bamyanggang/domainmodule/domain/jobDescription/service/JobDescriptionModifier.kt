package com.bamyanggang.domainmodule.domain.jobDescription.service

import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.JobDescription
import com.bamyanggang.domainmodule.domain.jobDescription.repository.JobDescriptionRepository
import org.springframework.stereotype.Service

@Service
class JobDescriptionModifier(
    private val jobDescriptionRepository: JobDescriptionRepository
) {

    fun modifyWriteStatus(jobDescription: JobDescription) {
        jobDescription.changeWriteStatus().also { jobDescriptionRepository.save(it) }
    }

}
