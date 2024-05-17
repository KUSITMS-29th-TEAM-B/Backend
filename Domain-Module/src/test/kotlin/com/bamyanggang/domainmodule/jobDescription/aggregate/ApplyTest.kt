package com.bamyanggang.domainmodule.jobDescription.aggregate

import com.bamyanggang.commonmodule.fixture.generateBasicTypeFixture
import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.Apply
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.*

class ApplyTest : FunSpec({
    test("Apply.create 생성") {
        val title: String = generateBasicTypeFixture(10)
        val jobDescriptionId: UUID = generateFixture()

        val apply = Apply.create(title, jobDescriptionId)

        apply.title shouldBe title
        apply.jobDescriptionId shouldBe jobDescriptionId
        apply.writeStatus shouldBe WriteStatus.WRITING
    }

    test("Apply.create 입력값으로 공백이 들어오면 에러 반환") {
        val title = ""
        val jobDescriptionId: UUID = generateFixture()

        shouldThrow<IllegalArgumentException> {
            Apply.create(title, jobDescriptionId)
        }
    }
})
