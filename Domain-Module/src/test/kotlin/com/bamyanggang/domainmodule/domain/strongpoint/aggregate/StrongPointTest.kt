package com.bamyanggang.domainmodule.strongpoint.aggregate

import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.StrongPoint
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.*

class StrongPointTest: FunSpec({
    val userId: UUID = generateFixture()

    test("역량 키워드 정상 생성 테스트") {
        //arange
        val name = "역량 키워드"
        val newStrongPoint = StrongPoint.create(name, userId)

        newStrongPoint.name shouldBe name
        newStrongPoint.userId shouldBe userId
    }

    test("이름 공백 역량 키워드 등록 예외 테스트"){
        shouldThrow<IllegalArgumentException> {
            StrongPoint.create("", userId)
        }
    }

    test("역량 키워드 이름 중복 검증 테스트") {
        val name = "중복 역량 키워드"
        val originStrongPoint = StrongPoint.create(name, userId)

        originStrongPoint.isDuplicated(name) shouldBe true
    }

})
