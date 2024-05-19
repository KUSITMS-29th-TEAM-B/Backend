package com.bamyanggang.apimodule.domain.jobDescription.application

import com.bamyanggang.apimodule.domain.jobDescription.application.dto.CreateApply
import com.bamyanggang.apimodule.domain.jobDescription.application.service.ApplyCreateService
import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.Apply
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.JobDescription
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import com.bamyanggang.domainmodule.domain.jobDescription.service.ApplyAppender
import com.bamyanggang.domainmodule.domain.jobDescription.service.ApplyContentAppender
import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionModifier
import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionReader
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.time.LocalDateTime
import java.util.*



class ApplyCreateServiceTest : BehaviorSpec({
    val applyAppender = mockk<ApplyAppender>()
    val applyContentAppender = mockk<ApplyContentAppender>()
    val jobDescriptionReader = mockk<JobDescriptionReader>()
    val jobDescriptionModifier = mockk<JobDescriptionModifier>()
    val applyCreateService = ApplyCreateService(applyAppender, applyContentAppender, jobDescriptionReader, jobDescriptionModifier)

    Given("CreateApply.Request 와 jobDescriptionId이 들어온 경우") {
        val request = generateFixture<CreateApply.Request>()
        val jobDescriptionId = UUID.randomUUID()
        val jobDescription : JobDescription = generateFixture{
            it.set("id", jobDescriptionId)
            it.set("enterpriseName", "기업 이름")
            it.set("title", "직무 공고 제목")
            it.set("content", "직무 공고 내용")
            it.set("link", "직무 공고 링크")
            it.set("startedAt", LocalDateTime.now())
            it.set("endedAt", LocalDateTime.now().plusDays(1))
        }
        val apply = generateFixture<Apply>()

        every { jobDescriptionReader.readJobDescriptionById(jobDescriptionId) } returns jobDescription
        every { applyAppender.appendApply(jobDescriptionId) } returns apply
        every { applyContentAppender.appendApplyContent(any(), any(), any()) } returns Unit
        every { jobDescriptionModifier.modifyWriteStatus(any(), any()) } returns Unit

        When("createApply가 호출된다") {
            applyCreateService.createApply(request, jobDescriptionId)

            Then("applyAppender.appendApply가 호출된다") {
                verify { applyAppender.appendApply(jobDescriptionId) }
            }

            Then("applyContentAppender.appendApplyContent가 호출된다") {
                request.contents.forEach { content ->
                    verify {
                        applyContentAppender.appendApplyContent(
                            applyId = apply.id,
                            question = content.question,
                            answer = content.answer
                        )
                    }
                }
            }

            Then("jobDescriptionModifier.modifyWriteStatus 가 호출된다") {
                verify { jobDescriptionModifier.modifyWriteStatus(jobDescription, WriteStatus.WRITING) }
            }
        }
    }
})
