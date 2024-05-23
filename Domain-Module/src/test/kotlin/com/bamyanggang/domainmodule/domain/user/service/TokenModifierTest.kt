package com.bamyanggang.domainmodule.domain.user.service

import com.bamyanggang.domainmodule.domain.user.aggregate.Token
import com.bamyanggang.domainmodule.domain.user.repository.TokenRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.UUID

class TokenModifierTest : BehaviorSpec({
    val mockTokenRepository = mockk<TokenRepository>(relaxed = true)
    val tokenModifier = TokenModifier(mockTokenRepository)
    val userId = UUID.randomUUID()
    val refreshToken = "refreshToken"
    val newRefreshToken = "newRefreshToken"

    Given("a user id, a refresh token, and a new refresh token") {
        val mockToken = mockk<Token>(relaxed = true)
        every { mockTokenRepository.findByValue(refreshToken) } returns mockToken

        When("modifyToken is called") {
            tokenModifier.modifyToken(userId, refreshToken, newRefreshToken)

            Then("the token should be updated and saved") {
                verify { mockTokenRepository.findByValue(refreshToken) }
                verify { mockToken.update(userId, newRefreshToken) }
                verify { mockTokenRepository.save(any()) }
            }
        }
    }
})
