package com.bamyanggang.domainmodule.domain.user.aggregate

import com.bamyanggang.domainmodule.domain.user.enums.SocialLoginProvider
import com.bamyanggang.domainmodule.common.entity.AggregateRoot
import com.example.uuid.UuidCreator
import java.time.LocalDateTime
import java.util.UUID

data class User (
    override val id: UUID = UuidCreator.create(),
    val socialId: String,
    val profileImgUrl: String,
    val provider: SocialLoginProvider,
    val nickName: String,
    val jobSearchStatus: String? = null,
    val desiredJob: String? = null,
    val goal: String? = null,
    val dream: String? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) : AggregateRoot {

    init {
        require(profileImgUrl.isNotBlank()) { "프로필 이미지는 필수입니다." }
        require(nickName.isNotBlank()) { "닉네임은 필수입니다." }
        require(nickName.length <= 10) { "닉네임은 최대 10자까지 작성이 가능합니다." }
        require(desiredJob?.length ?: 0 <= 50) { "희망 직무는 최대 50자까지 작성이 가능합니다." }
        require(goal?.length ?: 0 <= 400) { "발전시키고 싶은 역량은 최대 400자까지 작성이 가능합니다." }
        require(dream?.length ?: 0 <= 400) { "꿈은 최대 400자까지 작성이 가능합니다." }
    }

    companion object {
        fun create(
            socialId: String,
            profileImgUrl: String,
            provider: SocialLoginProvider,
            nickName: String,
            jobSearchStatus: String?,
            desiredJob: String?,
            goal: String?,
            dream: String?,
        ): User {
            return User(
                socialId = socialId,
                profileImgUrl = profileImgUrl,
                provider = provider,
                nickName = nickName,
                jobSearchStatus = jobSearchStatus,
                desiredJob = desiredJob,
                goal = goal,
                dream = dream,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
            )
        }

        fun toDomain(
            id: UUID,
            socialId: String,
            profileImgUrl: String,
            provider: SocialLoginProvider,
            nickName: String,
            jobSearchStatus: String?,
            desiredJob: String?,
            goal: String?,
            dream: String?,
            createdAt: LocalDateTime,
            updatedAt: LocalDateTime
        ): User {
            return User(
                id = id,
                socialId = socialId,
                profileImgUrl = profileImgUrl,
                provider = provider,
                nickName = nickName,
                jobSearchStatus = jobSearchStatus,
                desiredJob = desiredJob,
                goal = goal,
                dream = dream,
                createdAt = createdAt,
                updatedAt = updatedAt
            )
        }
    }
}