package com.bamyanggang.domainmodule.domain.jobDescription.service

import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.JobDescription
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import com.bamyanggang.domainmodule.domain.jobDescription.repository.JobDescriptionRepository
import org.springframework.stereotype.Service

@Service
class JobDescriptionModifier(
    private val jobDescriptionRepository: JobDescriptionRepository
) {

    fun modifyWriteStatus(jobDescription: JobDescription, writeStatus: WriteStatus) {
        jobDescription.changeWriteStatus(writeStatus).also { jobDescriptionRepository.save(it) }
    }

}
