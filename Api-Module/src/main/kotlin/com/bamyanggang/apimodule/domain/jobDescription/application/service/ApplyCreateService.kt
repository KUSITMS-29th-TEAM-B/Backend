package com.bamyanggang.apimodule.domain.jobDescription.application.service

import com.bamyanggang.apimodule.domain.jobDescription.application.dto.CreateApply
import com.bamyanggang.domainmodule.domain.jobDescription.service.ApplyAppender
import com.bamyanggang.domainmodule.domain.jobDescription.service.ApplyContentAppender
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ApplyCreateService(
    private val applyAppender: ApplyAppender,
    private val applyContentAppender: ApplyContentAppender
) {
    @Transactional
    fun createApply(request: CreateApply.Request, jobDescriptionId: UUID) {
        applyAppender.appendApply(
            jobDescriptionId = jobDescriptionId,
        ).also { apply ->
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
