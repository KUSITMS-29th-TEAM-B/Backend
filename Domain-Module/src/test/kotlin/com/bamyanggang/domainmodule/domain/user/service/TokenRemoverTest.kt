package com.bamyanggang.domainmodule.domain.user.service

import com.bamyanggang.domainmodule.domain.user.repository.TokenRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify

class TokenRemoverTest : BehaviorSpec({
    val mockTokenRepository = mockk<TokenRepository>(relaxed = true)
    val tokenRemover = TokenRemover(mockTokenRepository)
    val token = "token"

    Given("a token") {
        When("removeToken is called") {
            tokenRemover.removeToken(token)

            Then("the token should be removed") {
                verify { mockTokenRepository.deleteByValue(token) }
            }
        }
    }
})
