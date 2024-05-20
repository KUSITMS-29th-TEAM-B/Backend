package com.bamyanggang.apimodule.domain.jobDescription.application.service

import com.bamyanggang.apimodule.domain.jobDescription.application.dto.JobDescriptionInfo
import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionModifier
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class JobDescriptionInfoUpdateService(
    private val jobDescriptionModifier: JobDescriptionModifier,
) {
    @Transactional
    fun updateWriteStatus(jobDescriptionId: UUID) {
        jobDescriptionModifier.modifyWriteStatus(jobDescriptionId)
    }

    @Transactional
    fun updateJobDescriptionDetail(jobDescriptionId: UUID, request: JobDescriptionInfo.Request.Update) {
        jobDescriptionModifier.modifyJobDescription(
            jobDescriptionId, request.enterpriseName,
            request.title, request.content,
            request.link, request.startedAt, request.endedAt)
    }

}
