package com.bamyanggang.domainmodule.domain.jobDescription.service

import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.jobDescription.repository.JobDescriptionRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.time.LocalDateTime
import java.util.*

class JobDescriptionAppenderTest : BehaviorSpec({
    val mockJobDescriptionRepository = mockk<JobDescriptionRepository>(relaxed = true)
    val jobDescriptionAppender = JobDescriptionAppender(mockJobDescriptionRepository)

    given("JobDescriptionAppender.appendJobDescription") {
        val enterpriseName= "기업 명"
        val title: String = generateFixture { it.set("title", "직무 공고 제목") }
        val content: String = generateFixture { it.set("content", "직무 공고 내용") }
        val link: String = generateFixture { it.set("link", "직무 공고 링크") }
        val startedAt: LocalDateTime = LocalDateTime.now()
        val endedAt: LocalDateTime = startedAt.plusDays(1)
        val userId : UUID = UUID.randomUUID()

        `when`("appendJobDescription이 호출되면") {
            jobDescriptionAppender.appendJobDescription(
                enterpriseName = enterpriseName,
                title = title,
                content = content,
                link = link,
                startedAt = startedAt,
                endedAt = endedAt,
                userId = userId
            )

            then("JobDescription.create와 jobDescriptionRepository.save가 호출된다.") {
                verify {
                    mockJobDescriptionRepository.save(match { jobDescription ->
                        jobDescription.enterpriseName == enterpriseName &&
                                jobDescription.title == title &&
                                jobDescription.content == content &&
                                jobDescription.link == link &&
                                jobDescription.startedAt == startedAt &&
                                jobDescription.endedAt == endedAt &&
                                jobDescription.userId == userId
                    })
                }
            }
        }
    }

})
