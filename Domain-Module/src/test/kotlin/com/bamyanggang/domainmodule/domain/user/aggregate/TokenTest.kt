package com.bamyanggang.domainmodule.domain.user.aggregate

import com.bamyanggang.commonmodule.fixture.generateFixture
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.UUID

class TokenTest : FunSpec({

    test("Token 생성") {
        val token: Token = generateFixture {
            it.set("userId", UUID.randomUUID())
            it.set("value", "token")
        }
        token.value shouldBe "token"
    }

    test("Token 업데이트") {
        val userId = UUID.randomUUID()
        val token: Token = generateFixture {
            it.set("userId", userId)
            it.set("value", "token")
        }
        val newToken = "newToken"
        val updatedToken = token.update(userId, newToken)
        updatedToken.value shouldBe newToken
    }

})
