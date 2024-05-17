package com.bamyanggang.domainmodule.domain.jobDescription.service

import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.ApplyContent
import com.bamyanggang.domainmodule.domain.jobDescription.repository.ApplyContentRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ApplyContentAppender(
    private val applyContentRepository: ApplyContentRepository
) {
    fun appendApplyContent(applyId: UUID, question: String, answer: String) {
        ApplyContent.create(
            question = question,
            answer = answer,
            applyId = applyId
        ).also { applyContentRepository.save(it) }
    }

}
