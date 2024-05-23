package com.bamyanggang.domainmodule.domain.user.service

import com.bamyanggang.domainmodule.domain.user.aggregate.User
import com.bamyanggang.domainmodule.domain.user.repository.UserRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.UUID

class UserReaderTest : BehaviorSpec({
    val mockUserRepository = mockk<UserRepository>(relaxed = true)
    val userReader = UserReader(mockUserRepository)
    val socialId = "socialId"
    val userId = UUID.randomUUID()

    Given("a social id") {
        val mockUser = mockk<User>(relaxed = true)
        every { mockUserRepository.findBySocialId(socialId) } returns mockUser

        When("readUserBySocialId is called") {
            userReader.readUserBySocialId(socialId)

            Then("the user with the given social id should be returned") {
                verify { mockUserRepository.findBySocialId(socialId) }
            }
        }
    }

    Given("a user id") {
        val mockUser = mockk<User>(relaxed = true)
        every { mockUserRepository.findById(userId) } returns mockUser

        When("readUserById is called") {
            userReader.readUserById(userId)

            Then("the user with the given id should be returned") {
                verify { mockUserRepository.findById(userId) }
            }
        }
    }
})
