package com.bamyanggang.apimodule.domain.jobDescription.application

import com.bamyanggang.apimodule.domain.jobDescription.application.dto.CreateApply
import com.bamyanggang.apimodule.domain.jobDescription.application.service.ApplyCreateService
import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.Apply
import com.bamyanggang.domainmodule.domain.jobDescription.service.ApplyAppender
import com.bamyanggang.domainmodule.domain.jobDescription.service.ApplyContentAppender
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*


class ApplyCreateServiceTest : BehaviorSpec({
    val applyAppender = mockk<ApplyAppender>()
    val applyContentAppender = mockk<ApplyContentAppender>()
    val applyCreateService = ApplyCreateService(applyAppender, applyContentAppender)

    given("ApplyCreateService.createApply") {
        `when`("request가 주어지면") {
            val request: CreateApply.Request = generateFixture()
            val jobDescriptionId: UUID = generateFixture()
            val apply: Apply = generateFixture()
            every { applyAppender.appendApply(any()) } returns apply
            every { applyContentAppender.appendApplyContent(any(), any(), any()) } returns Unit

            applyCreateService.createApply(request, jobDescriptionId)

            then("appendApply가 호출된다.") {
                verify { applyAppender.appendApply( jobDescriptionId) }
            }

            then("appendApplyContent가 호출된다.") {
                request.contents.forEach { content ->
                    verify { applyContentAppender.appendApplyContent(apply.id, content.question, content.answer) }
                }
            }
        }
    }
})
