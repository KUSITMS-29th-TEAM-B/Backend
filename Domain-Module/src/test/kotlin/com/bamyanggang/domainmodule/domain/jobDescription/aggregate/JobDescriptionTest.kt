package com.bamyanggang.domainmodule.domain.jobDescription.aggregate

import com.bamyanggang.commonmodule.fixture.generateBasicTypeFixture
import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime
import java.util.*

class JobDescriptionTest : FunSpec({

    test("JobDescription 생성") {
        // arrange
        val enterpriseName: String = "기업 명"
        val title: String = "제목"
        val content: String = "내용"
        val link: String = "링크"
        val startedAt: LocalDateTime = LocalDateTime.now()
        val endedAt: LocalDateTime = startedAt.plusDays(1)
        val userId : UUID = UUID.randomUUID()
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
        val userId : UUID = UUID.randomUUID()

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
        val enterpriseName: String = generateBasicTypeFixture(10)
        val title: String = generateBasicTypeFixture(10)
        val content: String = generateBasicTypeFixture(10)
        val link: String = generateBasicTypeFixture(10)
        val startedAt: LocalDateTime = LocalDateTime.now()
        val endedAt: LocalDateTime = startedAt.minusDays(1)
        val userId : UUID = UUID.randomUUID()

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

    test("남은 날짜 계산") {
        // arrange
        val startedAt: LocalDateTime = LocalDateTime.now()
        val endedAt: LocalDateTime = startedAt.plusDays(1)
        val jobDescription = JobDescription.create(
            enterpriseName = generateBasicTypeFixture(10),
            title = generateBasicTypeFixture(10),
            content = generateBasicTypeFixture(10),
            link = generateBasicTypeFixture(10),
            startedAt = startedAt,
            endedAt = endedAt,
            userId = UUID.randomUUID()
        )

        // act
        val remainingDate = jobDescription.getRemainingDate()

        // assert
        remainingDate shouldBe 1
    }

    test("상태 변경") {
        // arrange
        val jobDescription = JobDescription.create(
            enterpriseName = generateBasicTypeFixture(10),
            title = generateBasicTypeFixture(10),
            content = generateBasicTypeFixture(10),
            link = generateBasicTypeFixture(10),
            startedAt = LocalDateTime.now(),
            endedAt = LocalDateTime.now().plusDays(1),
            userId = UUID.randomUUID()
        )

        // act
        val changedJobDescription = jobDescription.changeWriteStatus(WriteStatus.WRITING)

        // assert
        changedJobDescription.writeStatus shouldBe WriteStatus.WRITING
    }

})
