package com.bamyanggang.apimodule.domain.jobDescription.application

import com.bamyanggang.apimodule.domain.jobDescription.application.dto.CreateApply
import com.bamyanggang.apimodule.domain.jobDescription.application.service.ApplyCreateService
import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.JobDescription
import com.bamyanggang.domainmodule.domain.jobDescription.service.ApplyAppender
import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionModifier
import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionReader
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.time.LocalDateTime
import java.util.*



class ApplyCreateServiceTest : BehaviorSpec({
    val applyAppender = mockk<ApplyAppender>(relaxed = true)
    val jobDescriptionReader = mockk<JobDescriptionReader>(relaxed = true)
    val jobDescriptionModifier = mockk<JobDescriptionModifier>(relaxed = true)
    val service = ApplyCreateService(applyAppender, jobDescriptionReader, jobDescriptionModifier)

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

        val jobDescription: JobDescription = generateFixture{
            it.set("enterpriseName", "기업 이름")
            it.set("title", "직무 공고 제목")
            it.set("content", "직무 공고 내용")
            it.set("link", "직무 공고 링크")
            it.set("startedAt", LocalDateTime.now())
            it.set("endedAt", LocalDateTime.now().plusDays(1))
            it.set("userId", UUID.randomUUID())
        }

        every { jobDescriptionReader.readJobDescriptionById(jobDescriptionId) } returns jobDescription

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
