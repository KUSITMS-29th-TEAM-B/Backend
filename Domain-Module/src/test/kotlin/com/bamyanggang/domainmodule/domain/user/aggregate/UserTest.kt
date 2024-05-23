package com.bamyanggang.domainmodule.domain.user.aggregate

import com.bamyanggang.commonmodule.fixture.generateBasicTypeFixture
import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.user.enums.SocialLoginProvider
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class UserTest : FunSpec({

    test("User 생성") {
        val socialId = "socialId"
        val profileImgUrl = "profileImgUrl"
        val provider: SocialLoginProvider = SocialLoginProvider.KAKAO
        val email = "email"
        val nickName = "nickName"
        val jobSearchStatus = null
        val desiredJob = null
        val goal = null
        val dream = null

        val user = User.create(
            socialId = socialId,
            profileImgUrl = profileImgUrl,
            provider = provider,
            email = email,
            nickName = nickName,
            jobSearchStatus = jobSearchStatus,
            desiredJob = desiredJob,
            goal = goal,
            dream = dream
        )

        user.socialId shouldBe socialId
        user.profileImgUrl shouldBe profileImgUrl
        user.provider shouldBe provider
        user.email shouldBe email
        user.nickName shouldBe nickName
        user.jobSearchStatus shouldBe jobSearchStatus
        user.desiredJob shouldBe desiredJob
        user.goal shouldBe goal
        user.dream shouldBe dream

    }

    test("User 업데이트") {
        val user: User = generateFixture {
            it.set("socialId", "socialId")
            it.set("profileImgUrl", "profileImgUrl")
            it.set("provider", SocialLoginProvider.KAKAO)
            it.set("email", "email")
            it.set("nickName", "nickName")
            it.set("createdAt", LocalDateTime.now())
            it.set("updatedAt", LocalDateTime.now())
        }
        val newNickName = "newNick"
        val updatedUser = user.update(
            nickName = newNickName,
            profileImgUrl = null,
            jobSearchStatus = null,
            desiredJob = null,
            goal = null,
            dream = null
        )
        updatedUser.nickName shouldBe newNickName
    }

    test("필수 항목이 누락되었을 경우 에러 반환") {
        // arrange
        val socialId = "socialId"
        val profileImgUrl = ""
        val provider: SocialLoginProvider = SocialLoginProvider.KAKAO
        val email = "email"
        val nickName = "nickName"
        val jobSearchStatus = null
        val desiredJob = null
        val goal = null
        val dream = null

        // act, assert
        shouldThrow<IllegalArgumentException> {
            User.create(
                socialId = socialId,
                profileImgUrl = profileImgUrl,
                provider = provider,
                email = email,
                nickName = nickName,
                jobSearchStatus = jobSearchStatus,
                desiredJob = desiredJob,
                goal = goal,
                dream = dream
            )
        }
    }

    test("닉네임이 10자를 초과할 경우 에러 반환") {
            // arrange
            val socialId = "socialId"
            val profileImgUrl= "profileImgUrl"
            val provider: SocialLoginProvider = SocialLoginProvider.KAKAO
            val email = "email"
            val nickName = "nickName12345"
            val jobSearchStatus = null
            val desiredJob = null
            val goal = null
            val dream = null

            // act, assert
            shouldThrow<IllegalArgumentException> {
                User.create(
                    socialId = socialId,
                    profileImgUrl = profileImgUrl,
                    provider = provider,
                    email = email,
                    nickName = nickName,
                    jobSearchStatus = jobSearchStatus,
                    desiredJob = desiredJob,
                    goal = goal,
                    dream = dream
                )
            }
        }

    test("희망 직무가 50자를 초과할 경우 에러 반환") {
            // arrange
            val socialId = "socialId"
            val profileImgUrl = "profileImgUrl"
            val provider: SocialLoginProvider = SocialLoginProvider.KAKAO
            val email = "email"
            val nickName = "nickName"
            val jobSearchStatus = null
            val desiredJob: String = generateBasicTypeFixture(51)
            val goal = null
            val dream = null

            // act, assert
            shouldThrow<IllegalArgumentException> {
                User.create(
                    socialId = socialId,
                    profileImgUrl = profileImgUrl,
                    provider = provider,
                    email = email,
                    nickName = nickName,
                    jobSearchStatus = jobSearchStatus,
                    desiredJob = desiredJob,
                    goal = goal,
                    dream = dream
                )
            }
        }

    test("발전시키고 싶은 역량이 400자를 초과할 경우 에러 반환") {
            // arrange
            val socialId = "socialId"
            val profileImgUrl = "profileImgUrl"
            val provider: SocialLoginProvider = SocialLoginProvider.KAKAO
            val email = "email"
            val nickName = "nickName"
            val jobSearchStatus = null
            val desiredJob = null
            val goal: String = generateBasicTypeFixture(401)
            val dream = null

            // act, assert
            shouldThrow<IllegalArgumentException> {
                User.create(
                    socialId = socialId,
                    profileImgUrl = profileImgUrl,
                    provider = provider,
                    email = email,
                    nickName = nickName,
                    jobSearchStatus = jobSearchStatus,
                    desiredJob = desiredJob,
                    goal = goal,
                    dream = dream
                )
            }
        }

})
