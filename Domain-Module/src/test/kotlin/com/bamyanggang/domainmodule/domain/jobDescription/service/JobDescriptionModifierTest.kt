package com.bamyanggang.domainmodule.domain.jobDescription.service

import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.JobDescription
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import com.bamyanggang.domainmodule.domain.jobDescription.repository.JobDescriptionRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.time.LocalDateTime
import java.util.*

class JobDescriptionModifierTest: BehaviorSpec({
    val jobDescriptionRepository = mockk<JobDescriptionRepository>(relaxed = true)
    val service = JobDescriptionModifier(jobDescriptionRepository)

    given("JobDescriptionModifier.modifyWriteStatus") {
        val jobDescription: JobDescription = generateFixture {
            it.set("enterpriseName", "기업 이름")
            it.set("title", "직무 공고 제목")
            it.set("writeStatus", WriteStatus.NOT_APPLIED)
            it.set("content", "직무 공고 내용")
            it.set("link", "직무 공고 링크")
            it.set("createdAt", LocalDateTime.now())
            it.set("updatedAt", LocalDateTime.now())
            it.set("startedAt", LocalDateTime.now())
            it.set("endedAt", LocalDateTime.now().plusDays(1))
            it.set("userId", UUID.randomUUID())
        }
        `when`("jobDescription가 주어지면") {
            service.modifyWriteStatus(jobDescription)
            then("changeWriteStatus가 호출된다.") {
                verify { jobDescriptionRepository.save(any()) }
            }
        }
    }

})
