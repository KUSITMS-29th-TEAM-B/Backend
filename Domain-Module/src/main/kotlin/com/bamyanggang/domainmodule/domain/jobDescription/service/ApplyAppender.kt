package com.bamyanggang.domainmodule.domain.jobDescription.service

import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.Apply
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.ApplyContent
import com.bamyanggang.domainmodule.domain.jobDescription.repository.ApplyRepository
import java.util.*

class ApplyAppender(
    private val applyRepository: ApplyRepository
) {
    fun appendApply(
        contents: List<ApplyContent>,
        jobDescriptionId: UUID
    ): Apply {
        return Apply.create(
            contents = contents,
            jobDescriptionId = jobDescriptionId
        ).also { applyRepository.save(it) }
    }


}
