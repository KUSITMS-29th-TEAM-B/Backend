package com.bamyanggang.domainmodule.domain.jobDescription.service

import com.bamyanggang.domainmodule.domain.jobDescription.repository.JobDescriptionRepository
import java.time.LocalDateTime
import java.util.UUID

class JobDescriptionModifier(
    private val jobDescriptionRepository: JobDescriptionRepository
) {

    fun modifyWriteStatus(jobDescriptionId: UUID) {
        jobDescriptionRepository
            .findById(jobDescriptionId)
            .changeWriteStatus()
            .also { jobDescriptionRepository.save(it) }
    }

    fun modifyJobDescription(
        jobDescriptionId: UUID,
        enterpriseName: String?,
        title: String?,
        content: String?,
        link: String?,
        startedAt: LocalDateTime?,
        endedAt: LocalDateTime?) {
        jobDescriptionRepository
            .findById(jobDescriptionId)
            .update(
                enterpriseName = enterpriseName,
                title = title,
                content = content,
                link = link,
                startedAt = startedAt,
                endedAt = endedAt
            )
            .also { jobDescriptionRepository.save(it) }
    }

    fun invoke() {
        jobDescriptionRepository.changeWriteStatus()
    }

}
