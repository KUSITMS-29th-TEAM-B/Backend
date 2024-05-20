package com.bamyanggang.apimodule.domain.jobDescription.application

import com.bamyanggang.apimodule.domain.jobDescription.application.dto.CreateApply
import com.bamyanggang.apimodule.domain.jobDescription.application.service.ApplyCreateService
import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.jobDescription.service.ApplyAppender
import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionModifier
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.util.*



class ApplyCreateServiceTest : BehaviorSpec({
    val applyAppender = mockk<ApplyAppender>(relaxed = true)
    val jobDescriptionModifier = mockk<JobDescriptionModifier>(relaxed = true)
    val service = ApplyCreateService(applyAppender, jobDescriptionModifier)

    given("ApplyCreateService.createApply") {
        val request: CreateApply.Request = generateFixture {
            it.set("contents", listOf(
                CreateApply.CreateApplyContent(
                    question = "질문1",
                    answer = "답변1"
                )
            ))
        }
        val jobDescriptionId: UUID = generateFixture()

        `when`("request와 jobDescriptionId가 주어지면") {
            service.createApply(request, jobDescriptionId)

            then("modifyWriteStatus와 appendApply가 호출된다.") {
                verify {
                    jobDescriptionModifier.modifyWriteStatus(any())
                    applyAppender.appendApply(any(), any())
                }
            }
        }
    }

})
