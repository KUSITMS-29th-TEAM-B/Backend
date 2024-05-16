package com.bamyanggang.apimodule.domain.jobDescription.application.service

import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.apimodule.domain.jobDescription.application.dto.CreateJobDescription
import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionAppender
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class JobDescriptionCreateService(
    private val jobDescriptionAppender: JobDescriptionAppender
) {

    @Transactional
    fun createJobDescription(request: CreateJobDescription.Request) {
        getAuthenticationPrincipal().also {
                jobDescriptionAppender.appendJobDescription(
                enterpriseName = request.enterpriseName,
                title = request.title,
                content = request.content,
                link = request.link,
                startedAt = request.startedAt,
                endedAt = request.endedAt,
                userId = it
            )
        }
    }
}
