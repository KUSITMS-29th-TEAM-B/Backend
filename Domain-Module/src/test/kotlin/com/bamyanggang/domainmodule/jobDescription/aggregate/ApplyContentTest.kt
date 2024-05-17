package com.bamyanggang.domainmodule.jobDescription.aggregate

import com.bamyanggang.commonmodule.fixture.generateBasicTypeFixture
import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.ApplyContent
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.*

class ApplyContentTest : FunSpec({

    test("ApplyContent 생성") {
        // arrange
        val question: String = generateBasicTypeFixture(10)
        val answer: String = generateBasicTypeFixture(10)
        val applyId: UUID = generateFixture()

        // act
        val applyContent = ApplyContent.create(
            question = question,
            answer = answer,
            applyId = applyId
        )

        // assert
        applyContent.question shouldBe question
        applyContent.answer shouldBe answer
    }

    test("applyContent 입력값으로 공백이 들어오면 에러 반환") {
        // arrange
        val question: String = ""
        val answer: String = ""
        val applyId: UUID = generateFixture()

        // act, assert
        shouldThrow<IllegalArgumentException> {
            ApplyContent.create(
                question = question,
                answer = answer,
                applyId = applyId
            )
        }
    }

})
