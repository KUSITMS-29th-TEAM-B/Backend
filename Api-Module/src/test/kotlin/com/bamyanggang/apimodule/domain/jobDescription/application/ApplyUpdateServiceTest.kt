package com.bamyanggang.apimodule.domain.jobDescription.application

import com.bamyanggang.apimodule.domain.jobDescription.application.dto.ApplyInfo
import com.bamyanggang.apimodule.domain.jobDescription.application.service.ApplyUpdateService
import com.bamyanggang.domainmodule.domain.jobDescription.service.ApplyModifier
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.util.UUID

class ApplyUpdateServiceTest: BehaviorSpec({
    val applyModifier = mockk<ApplyModifier>(relaxed = true)
    val service = ApplyUpdateService(applyModifier)

    given("ApplyUpdateService") {
        val jobDescriptionId = UUID.randomUUID()
        val request = ApplyInfo.Request.Update(
            contents = listOf(
                ApplyInfo.ContentInfo(
                    question = "질문1",
                    answer = "답변1"
                )
            )
        )
        `when`("updateApplyInfo") {
            service.updateApplyInfo(jobDescriptionId, request)
            then("should update apply info") {
                 verify { applyModifier.modifyApplyInfo(any(), any()) }
            }
        }
    }
})
