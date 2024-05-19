package com.bamyanggang.apimodule.domain.jobDescription.application.service

import com.bamyanggang.apimodule.domain.jobDescription.application.dto.CreateApply
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import com.bamyanggang.domainmodule.domain.jobDescription.service.ApplyAppender
import com.bamyanggang.domainmodule.domain.jobDescription.service.ApplyContentAppender
import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ApplyCreateService(
    private val applyAppender: ApplyAppender,
    private val applyContentAppender: ApplyContentAppender,
    private val jobDescriptionReader: JobDescriptionReader
) {
    @Transactional
    fun createApply(request: CreateApply.Request, jobDescriptionId: UUID) {
        jobDescriptionReader.readJobDescriptionById(jobDescriptionId).also {
            it.changeWriteStatus(WriteStatus.WRITING)
             applyAppender.appendApply(it.id).also { apply ->
                 request.contents.forEach { content ->
                     applyContentAppender.appendApplyContent(
                         applyId = apply.id,
                         question = content.question,
                         answer = content.answer
                     )
                 }
            }
        }
    }

}
