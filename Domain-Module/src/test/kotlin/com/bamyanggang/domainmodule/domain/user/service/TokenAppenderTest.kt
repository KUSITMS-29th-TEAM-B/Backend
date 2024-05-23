package com.bamyanggang.domainmodule.domain.user.service

import com.bamyanggang.domainmodule.domain.user.aggregate.Token
import com.bamyanggang.domainmodule.domain.user.repository.TokenRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.UUID

class TokenAppenderTest : BehaviorSpec({
    val mockTokenRepository = mockk<TokenRepository>(relaxed = true)
    val tokenAppender = TokenAppender(mockTokenRepository)
    val userId = UUID.randomUUID()
    val refreshToken = "refreshToken"

    Given("a user id and a refresh token, and the user does not have an existing token") {
        every { mockTokenRepository.findByUserId(userId) } returns null

        When("appendToken is called") {
            tokenAppender.appendToken(userId, refreshToken)

            Then("a new token should be created and saved") {
                verify { mockTokenRepository.findByUserId(userId) }
                verify { mockTokenRepository.save(any()) }
            }
        }
    }

    Given("a user id and a refresh token, and the user has an existing token") {
        val mockToken = mockk<Token>(relaxed = true)
        every { mockTokenRepository.findByUserId(userId) } returns mockToken

        When("appendToken is called") {
            tokenAppender.appendToken(userId, refreshToken)

            Then("the existing token should be updated and saved") {
                verify { mockTokenRepository.findByUserId(userId) }
                verify { mockToken.update(userId, refreshToken) }
                verify { mockTokenRepository.save(mockToken) }
            }
        }
    }
})
