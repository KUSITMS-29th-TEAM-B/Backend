package com.bamyanggang.domainmodule.domain.jobDescription.service

import com.bamyanggang.commonmodule.fixture.generateBasicTypeFixture
import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.jobDescription.repository.JobDescriptionRepository
import com.bamyanggang.domainmodule.domain.jobDescription.service.JobDescriptionAppender

import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.time.LocalDateTime
import java.util.*

class JobDescriptionAppenderTest : BehaviorSpec({
    val mockJobDescriptionRepository = mockk<JobDescriptionRepository>(relaxed = true)
    val jobDescriptionAppender = JobDescriptionAppender(mockJobDescriptionRepository)

    given("JobDescriptionAppender.appendJobDescription") {
        val enterpriseName : String = generateBasicTypeFixture(10)
        val title : String = generateBasicTypeFixture(10)
        val content : String = generateBasicTypeFixture(10)
        val link : String = generateBasicTypeFixture(10)
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
