package com.bamyanggang.domainmodule.domain.jobDescription.service

import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.Apply
import com.bamyanggang.domainmodule.domain.jobDescription.repository.ApplyRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ApplyAppender(
    private val applyRepository: ApplyRepository
) {
    fun appendApply(
        title: String,
        jobDescriptionId: UUID
    ): Apply {
        return Apply.create(
            jobDescriptionId = jobDescriptionId,
            title = title
        ).also { applyRepository.save(it) }
    }


}
