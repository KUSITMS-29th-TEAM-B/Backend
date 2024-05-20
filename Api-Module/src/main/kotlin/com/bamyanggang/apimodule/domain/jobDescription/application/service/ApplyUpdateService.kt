package com.bamyanggang.apimodule.domain.jobDescription.application.service

import com.bamyanggang.apimodule.domain.jobDescription.application.dto.ApplyInfo
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.ApplyContent
import com.bamyanggang.domainmodule.domain.jobDescription.service.ApplyModifier
import org.springframework.stereotype.Service
import java.util.*

@Service
class ApplyUpdateService(
    private val applyModifier: ApplyModifier
) {

    fun updateApplyInfo(jobDescriptionId: UUID, request: ApplyInfo.Request.Update) {
        applyModifier.modifyApplyInfo(
            jobDescriptionId = jobDescriptionId,
            applyContentList = request.contents.map {
                ApplyContent.create(
                    question = it.question,
                    answer = it.answer
                )
            }
        )
    }

}
