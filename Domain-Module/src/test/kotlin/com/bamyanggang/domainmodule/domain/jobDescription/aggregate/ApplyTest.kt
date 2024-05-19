package com.bamyanggang.domainmodule.domain.jobDescription.aggregate

import com.bamyanggang.commonmodule.fixture.generateBasicTypeFixture
import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.Apply
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.*

class
ApplyTest : FunSpec({
    test("Apply.create 생성") {
        val jobDescriptionId: UUID = UUID.randomUUID()

        val apply = Apply.create(jobDescriptionId)

        apply.jobDescriptionId shouldBe jobDescriptionId
    }

})