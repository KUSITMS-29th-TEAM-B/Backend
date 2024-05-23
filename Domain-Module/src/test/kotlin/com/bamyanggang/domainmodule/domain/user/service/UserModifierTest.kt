package com.bamyanggang.domainmodule.domain.user.service

import com.bamyanggang.domainmodule.domain.user.aggregate.User
import com.bamyanggang.domainmodule.domain.user.repository.UserRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.UUID

class UserModifierTest : BehaviorSpec({
    val mockUserRepository = mockk<UserRepository>(relaxed = true)
    val userModifier = UserModifier(mockUserRepository)
    val userId = UUID.randomUUID()
    val nickName = "nickName"
    val profileImgUrl = "profileImgUrl"
    val jobSearchStatus = "jobSearchStatus"
    val desiredJob = "desiredJob"
    val goal = "goal"
    val dream = "dream"

    Given("a user id, nickname, profile image url, job search status, desired job, goal, and dream") {
        val mockUser = mockk<User>(relaxed = true)
        every { mockUserRepository.findById(userId) } returns mockUser

        When("modifyUserInfo is called") {
            userModifier.modifyUserInfo(userId, nickName, profileImgUrl, jobSearchStatus, desiredJob, goal, dream)

            Then("the user info should be updated and saved") {
                verify { mockUserRepository.findById(userId) }
                verify { mockUser.update(nickName, profileImgUrl, jobSearchStatus, desiredJob, goal, dream) }
                verify { mockUserRepository.save(any()) }
            }
        }
    }
})
