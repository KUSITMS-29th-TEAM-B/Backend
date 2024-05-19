package com.bamyanggang.domainmodule.domain.jobDescription.aggregate

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
        val contents = listOf(
            ApplyContent.create(
                question = generateFixture { it.set("question", "질문") },
                answer = generateFixture { it.set("answer", "답변") }
            )
        )
        val jobDescriptionId: UUID = UUID.randomUUID()

        val apply = Apply.create(contents,jobDescriptionId)

        apply.jobDescriptionId shouldBe jobDescriptionId
    }

})
