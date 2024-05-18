package com.bamyanggang.domainmodule.domain.experience.aggregate

import com.bamyanggang.commonmodule.fixture.generateBasicTypeFixture
import com.bamyanggang.commonmodule.fixture.generateFixture
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime
import java.util.*

class ExperienceTest : FunSpec({
    test("Experience 정상 생성 테스트"){
        val title: String = generateBasicTypeFixture(15)
        val parentTagId: UUID = generateFixture()
        val childTagId: UUID = generateFixture()
        val strongPointIds: List<UUID> = generateFixture()
        val startedAt: LocalDateTime = generateFixture()
        val endedAt: LocalDateTime = generateFixture()
        val contentIds: List<UUID> = generateFixture()
        val userId : UUID = generateFixture()

        val newExperience = Experience.create(
            title = title,
            userId = userId,
            parentTagId = parentTagId,
            childTagId = childTagId,
            strongPointIds = strongPointIds,
            contentIds = contentIds,
            startedAt = startedAt,
            endedAt = endedAt,
        )

        title shouldBe newExperience.title
        userId shouldBe newExperience.userId
        parentTagId shouldBe newExperience.parentTagId
        childTagId shouldBe newExperience.childTagId
        strongPointIds shouldBe strongPointIds
        contentIds shouldBe newExperience.contentIds
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
                strongPointIds = generateFixture(),
                contentIds = generateFixture(),
                startedAt = generateFixture(),
                endedAt = generateFixture(),
            )
        }
    }

    test("역량 키워드 개수 제한 예외 테스트") {
        val strongPointIds: List<UUID> = List(6) { generateFixture() }

        shouldThrow<IllegalArgumentException> {
            Experience.create(
                title = generateFixture(),
                userId = generateFixture(),
                parentTagId = generateFixture(),
                childTagId = generateFixture(),
                strongPointIds = strongPointIds,
                contentIds = generateFixture(),
                startedAt = generateFixture(),
                endedAt = generateFixture(),
            )
        }
    }
})
