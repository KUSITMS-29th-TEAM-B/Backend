package com.bamyanggang.domainmodule.jobDescription.aggregate

import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.JobDescription
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime
import java.util.UUID

class JobDescriptionTest : FunSpec({

    test("JobDescription 생성") {
        // arrange
        val enterpriseName: String = generateFixture()
        val title: String = generateFixture()
        val content: String = generateFixture()
        val link: String = generateFixture()
        val startedAt: LocalDateTime = generateFixture()
        val endedAt: LocalDateTime = generateFixture()
        val userId : UUID = generateFixture()

        // act
        val jobDescription = JobDescription.create(
            enterpriseName = enterpriseName,
            title = title,
            content = content,
            link = link,
            startedAt = startedAt,
            endedAt = endedAt,
            userId = userId
        )

        // assert
        jobDescription.enterpriseName shouldBe enterpriseName
        jobDescription.title shouldBe title
        jobDescription.content shouldBe content
        jobDescription.link shouldBe link
        jobDescription.startedAt shouldBe startedAt
        jobDescription.endedAt shouldBe endedAt
    }

    test("jobDescription 입력값으로 공백이 들어오면 에러 반환")
    {
        // arrange
        val enterpriseName: String = ""
        val title: String = ""
        val content: String = ""
        val link: String = ""
        val startedAt: LocalDateTime = generateFixture()
        val endedAt: LocalDateTime = generateFixture()
        val userId : UUID = generateFixture()

        // act, assert
        shouldThrow<IllegalArgumentException> {
            JobDescription.create(
                enterpriseName = enterpriseName,
                title = title,
                content = content,
                link = link,
                startedAt = startedAt,
                endedAt = endedAt,
                userId = userId
            )
        }
    }

    test("시작일이 종료일보다 늦을 경우 에러 반환")
    {
        // arrange
        val enterpriseName: String = generateFixture()
        val title: String = generateFixture()
        val content: String = generateFixture()
        val link: String = generateFixture()
        val startedAt: LocalDateTime = LocalDateTime.now()
        val endedAt: LocalDateTime = startedAt.minusDays(1)
        val userId : UUID = generateFixture()

        // act, assert
        shouldThrow<IllegalArgumentException> {
            JobDescription.create(
                enterpriseName = enterpriseName,
                title = title,
                content = content,
                link = link,
                startedAt = startedAt,
                endedAt = endedAt,
                userId = userId
            )
        }
    }

})
