package com.bamyanggang.apimodule.domain.jobDescription.application

import com.bamyanggang.apimodule.domain.jobDescription.application.dto.JobDescriptionInfo
import com.bamyanggang.apimodule.domain.jobDescription.application.service.JobDescriptionInfoGetService
import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.JobDescription
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import com.bamyanggang.domainmodule.domain.jobDescription.service.ApplyReader
import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionReader
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.time.LocalDateTime
import java.util.*

class JobDescriptionInfoGetServiceTest : BehaviorSpec({
    val jobDescriptionReader = mockk<JobDescriptionReader>()
    val applyReader = mockk<ApplyReader>()
    val jobDescriptionInfoGetService = JobDescriptionInfoGetService(jobDescriptionReader, applyReader)

    Given("jobDescription의 writeStatus가 CLOSED인 경우") {
        val jobDescriptionId = UUID.randomUUID()
        val jobDescription : JobDescription = generateFixture {
            it.set("enterpriseName", "기업 이름")
            it.set("title", "직무 공고 제목")
            it.set("content", "직무 공고 내용")
            it.set("link", "직무 공고 링크")
            it.set("writeStatus", WriteStatus.CLOSED)
            it.set("startedAt", LocalDateTime.now())
            it.set("endedAt", LocalDateTime.now().plusDays(1))
        }
        every { jobDescriptionReader.readJobDescriptionById(jobDescriptionId) } returns jobDescription
        every { applyReader.readApplyExists(jobDescriptionId) } returns true

        When("getJobDescriptionDetail가 호출된다") {
            val result = jobDescriptionInfoGetService.getJobDescriptionDetail(jobDescriptionId)

            Then("jobDescriptionReader.readJobDescriptionById가 호출된다") {
                verify { jobDescriptionReader.readJobDescriptionById(jobDescriptionId) }
            }

            And("applyReader.readApplyExists가 호출된다") {
                verify { applyReader.readApplyExists(jobDescriptionId) }
            }

            And("예상된 JobDescriptionInfo.Response.Detail 객체가 반환된다") {
                result shouldBe JobDescriptionInfo.Response.Detail(
                    jobDescription.getRemainingDate(),
                    jobDescription.enterpriseName,
                    jobDescription.title,
                    jobDescription.content,
                    jobDescription.link,
                    jobDescription.writeStatus,
                    jobDescription.createdAt,
                    jobDescription.startedAt,
                    jobDescription.endedAt,
                    true
                )
            }
        }
    }

    Given("jobDescription의 writeStatus가 WRITING인 경우") {
        val jobDescriptionId = UUID.randomUUID()
        val jobDescription : JobDescription = generateFixture {
            it.set("enterpriseName", "기업 이름")
            it.set("title", "직무 공고 제목")
            it.set("content", "직무 공고 내용")
            it.set("link", "직무 공고 링크")
            it.set("writeStatus", WriteStatus.WRITING)
            it.set("startedAt", LocalDateTime.now())
            it.set("endedAt", LocalDateTime.now().plusDays(1))
        }
        every { jobDescriptionReader.readJobDescriptionById(jobDescriptionId) } returns jobDescription

        When("getJobDescriptionDetail가 호출된다") {
            val result = jobDescriptionInfoGetService.getJobDescriptionDetail(jobDescriptionId)

            Then("jobDescriptionReader.readJobDescriptionById가 호출된다") {
                verify { jobDescriptionReader.readJobDescriptionById(jobDescriptionId) }
            }

            And("applyReader.readApplyExists는 호출되지 않는다") {
                verify(exactly = 0) { applyReader.readApplyExists(jobDescriptionId) }
            }

            And("예상된 JobDescriptionInfo.Response.Detail 객체가 반환된다") {
                result shouldBe JobDescriptionInfo.Response.Detail(
                    jobDescription.getRemainingDate(),
                    jobDescription.enterpriseName,
                    jobDescription.title,
                    jobDescription.content,
                    jobDescription.link,
                    jobDescription.writeStatus,
                    jobDescription.createdAt,
                    jobDescription.startedAt,
                    jobDescription.endedAt,
                    true
                )
            }
        }
    }


})
