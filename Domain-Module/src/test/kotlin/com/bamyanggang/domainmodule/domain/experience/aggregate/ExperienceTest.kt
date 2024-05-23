package com.bamyanggang.domainmodule.domain.experience.aggregate

import com.bamyanggang.commonmodule.fixture.generateFixture
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime
import java.util.*

class ExperienceTest : FunSpec({

    test("Experience 정상 생성 테스트"){
        val title = "경험 제목"
        val parentTagId: UUID = UUID.randomUUID()
        val childTagId: UUID = UUID.randomUUID()
        val contents: List<ExperienceContent> = generateFixture()
        val experienceStrongPoints: List<ExperienceStrongPoint> = generateFixture()
        val startedAt: LocalDateTime = generateFixture()
        val endedAt: LocalDateTime = generateFixture()
        val userId : UUID = generateFixture()

        val newExperience = Experience.create(
            title = title,
            parentTagId = parentTagId,
            childTagId = childTagId,
            contents = contents,
            experienceStrongPoints = experienceStrongPoints,
            userId = userId,
            startedAt = startedAt,
            endedAt = endedAt,
        )

        title shouldBe newExperience.title
        userId shouldBe newExperience.userId
        parentTagId shouldBe newExperience.parentTagId
        childTagId shouldBe newExperience.childTagId
        contents shouldBe contents
        experienceStrongPoints shouldBe experienceStrongPoints
        startedAt shouldBe startedAt
        endedAt shouldBe endedAt
    }

    test("제목 길이 제한 예외 테스트"){
        val title: String = List(51) { it.toString() }.joinToString("")

        shouldThrow<IllegalArgumentException> {
            Experience.create(
                title = title,
                userId = generateFixture(),
                parentTagId = generateFixture(),
                childTagId = generateFixture(),
                contents = generateFixture<List<ExperienceContent>>(),
                experienceStrongPoints = generateFixture<List<ExperienceStrongPoint>>(),
                startedAt = generateFixture(),
                endedAt = generateFixture(),
            )
        }
    }

    test("날짜 입력 예외 처리 테스트") {
        val title = "경험 제목"
        val parentTagId: UUID = UUID.randomUUID()
        val childTagId: UUID = UUID.randomUUID()
        val contents: List<ExperienceContent> = generateFixture()
        val experienceStrongPoints: List<ExperienceStrongPoint> = generateFixture()
        val userId : UUID = generateFixture()

        val startedAt = LocalDateTime.now()
        val endedAt = startedAt.minusDays(1)

        shouldThrow<IllegalArgumentException> {
            Experience.create(
                title = title,
                userId = userId,
                parentTagId = parentTagId,
                childTagId = childTagId,
                contents = contents,
                experienceStrongPoints = experienceStrongPoints,
                startedAt = startedAt,
                endedAt = endedAt,
            )
        }
    }
})
