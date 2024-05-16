package com.bamyanggang.domainmodule.domain.jobDescription.service

import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.JobDescription
import com.bamyanggang.domainmodule.domain.jobDescription.repository.JobDescriptionRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class JobDescriptionAppender(
    private val jobDescriptionRepository: JobDescriptionRepository
) {
    fun appendJobDescription(
        enterpriseName: String,
        title: String,
        content: String,
        link: String,
        startedAt: LocalDateTime,
        endedAt: LocalDateTime,
        userId: UUID
    ): JobDescription {
        return JobDescription.create(
            enterpriseName = enterpriseName,
            title = title,
            content = content,
            link = link,
            startedAt = startedAt,
            endedAt = endedAt,
            userId = userId
        ).also { jobDescriptionRepository.save(it) }
    }
}
