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
        val jobDescriptionId: UUID = UUID.randomUUID()
        `when`("jobDescription가 주어지면") {
            service.modifyWriteStatus(jobDescriptionId)
            then("changeWriteStatus가 호출된다.") {
                verify { jobDescriptionRepository.save(any()) }
            }
        }
    }

    given("JobDescriptionModifier.modifyJobDescription") {
        val jobDescriptionId: UUID = UUID.randomUUID()
        val enterpriseName: String = generateFixture { it.set("enterpriseName", "기업명") }
        val title: String = generateFixture { it.set("title", "제목") }
        val content: String = generateFixture { it.set("content", "내용") }
        val link: String = generateFixture { it.set("link", "링크") }
        val startedAt: LocalDateTime = LocalDateTime.now()
        val endedAt: LocalDateTime = startedAt.plusDays(1)
        `when`("jobDescription가 주어지면") {
            service.modifyJobDescription(
                jobDescriptionId,
                enterpriseName,
                title,
                content,
                link,
                startedAt,
                endedAt
            )
            then("update가 호출된다.") {
                verify { jobDescriptionRepository.save(any()) }
            }
        }
    }

})
