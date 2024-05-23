package com.bamyanggang.domainmodule.domain.user.service

import com.bamyanggang.domainmodule.domain.user.enums.SocialLoginProvider
import com.bamyanggang.domainmodule.domain.user.exception.UserException
import com.bamyanggang.domainmodule.domain.user.repository.UserRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class UserAppenderTest : BehaviorSpec({
    val mockUserRepository = mockk<UserRepository>(relaxed = true)
    val userAppender = UserAppender(mockUserRepository)
    val socialId = "socialId"
    val profileImgUrl = "profileImgUrl"
    val provider = SocialLoginProvider.KAKAO
    val email = "email"
    val nickName = "nickName"
    val jobSearchStatus = "jobSearchStatus"
    val desiredJob = "desiredJob"
    val goal = "goal"
    val dream = "dream"

    Given("a social id, profile image url, provider, email, nickname, job search status, desired job, goal, and dream") {
        every { mockUserRepository.existsBySocialId(socialId) } returns false

        When("appendUser is called") {
            userAppender.appendUser(socialId, profileImgUrl, provider, email, nickName, jobSearchStatus, desiredJob, goal, dream)

            Then("a new user should be created and saved") {
                verify { mockUserRepository.existsBySocialId(socialId) }
                verify { mockUserRepository.save(any()) }
            }
        }
    }

    Given("a social id that already exists") {
        every { mockUserRepository.existsBySocialId(socialId) } returns true

        When("appendUser is called") {
            val exception = shouldThrow<UserException.DuplicatedUser> {
                userAppender.appendUser(socialId, profileImgUrl, provider, email, nickName, jobSearchStatus, desiredJob, goal, dream)
            }

            Then("a DuplicatedUser exception should be thrown") {
                verify { mockUserRepository.existsBySocialId(socialId) }
                assert(exception.message == "이미 존재하는 유저입니다.")
            }
        }
    }

})
