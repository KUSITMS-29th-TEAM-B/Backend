package com.bamyanggang.domainmodule.domain.jobDescription.service

import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.ApplyContent
import com.bamyanggang.domainmodule.domain.jobDescription.repository.ApplyRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ApplyModifier(
    private val applyRepository: ApplyRepository
) {

    fun modifyApplyInfo(
        jobDescriptionId: UUID,
        applyContentList: List<ApplyContent>
    ) {
        applyRepository
            .findByJobDescriptionId(jobDescriptionId)
            .update(
                contents = applyContentList
            )
            .also { applyRepository.save(it) }
    }


}
