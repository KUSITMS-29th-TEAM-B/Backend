package com.bamyanggang.apimodule.domain.jobDescription.application.service

import com.bamyanggang.apimodule.domain.jobDescription.application.dto.CreateApply
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.ApplyContent
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import com.bamyanggang.domainmodule.domain.jobDescription.service.ApplyAppender
import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionModifier
import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ApplyCreateService(
    private val applyAppender: ApplyAppender,
    private val jobDescriptionReader: JobDescriptionReader,
    private val jobDescriptionModifier: JobDescriptionModifier
) {
    @Transactional
    fun createApply(request: CreateApply.Request, jobDescriptionId: UUID) {
        val applyContents: List<ApplyContent> = request.contents.map {
            ApplyContent.create(
                question = it.question,
                answer = it.answer
            )
        }

        val jobDescription = jobDescriptionReader.readJobDescriptionById(jobDescriptionId)
        jobDescriptionModifier.modifyWriteStatus(jobDescription)
        applyAppender.appendApply(contents = applyContents, jobDescriptionId = jobDescriptionId)
    }

}
