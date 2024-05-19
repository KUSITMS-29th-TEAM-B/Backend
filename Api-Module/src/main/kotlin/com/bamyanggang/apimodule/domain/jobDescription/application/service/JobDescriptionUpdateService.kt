package com.bamyanggang.apimodule.domain.jobDescription.application.service

import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionModifier
import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionReader
import org.springframework.stereotype.Service
import java.util.*

@Service
class JobDescriptionUpdateService(
    private val jobDescriptionModifier: JobDescriptionModifier,
    private val jobDescriptionReader: JobDescriptionReader
) {
    fun updateWriteStatus(jobDescriptionId: UUID) {
        val jobDescription = jobDescriptionReader.readJobDescriptionById(jobDescriptionId)
        jobDescriptionModifier.modifyWriteStatus(jobDescription)
    }

}
