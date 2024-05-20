package com.bamyanggang.apimodule.domain.jobDescription.application

import com.bamyanggang.apimodule.domain.jobDescription.application.dto.JobDescriptionInfo
import com.bamyanggang.apimodule.domain.jobDescription.application.service.JobDescriptionInfoUpdateService
import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionModifier
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.time.LocalDateTime
import java.util.UUID

class JobDescriptionUpdateServiceTest: BehaviorSpec({
    val jobDescriptionModifier = mockk<JobDescriptionModifier>(relaxed = true)
    val service = JobDescriptionInfoUpdateService(jobDescriptionModifier)

    given("JobDescriptionUpdateService.updateWriteStatus") {
        val jobDescriptionId = UUID.randomUUID()
        `when`("직무 공고 id가 주어지면") {
            service.updateWriteStatus(jobDescriptionId)
            then("modifyWriteStatus가 호출된다.") {
                verify { jobDescriptionModifier.modifyWriteStatus(any()) }
            }
        }
    }

    given("JobDescriptionUpdateService.updateJobDescriptionDetail") {
        val jobDescriptionId = UUID.randomUUID()
        val request: JobDescriptionInfo.Request.Update = generateFixture {
            it.set("enterpriseName", "기업명")
            it.set("title", "제목")
            it.set("content", "내용")
            it.set("link", "링크")
            it.set("startedAt",LocalDateTime.now())
            it.set("endedAt",LocalDateTime.now().plusDays(1))
        }
        `when`("직무 공고 id와 request가 주어지면") {
            service.updateJobDescriptionDetail(jobDescriptionId, request)
            then("modifyJobDescription이 호출된다.") {
                verify { jobDescriptionModifier.modifyJobDescription(any(), any(), any(), any(), any(), any(), any()) }
            }
        }
    }

})
