package com.bamyanggang.domainmodule.domain.user.aggregate

import com.bamyanggang.domainmodule.domain.user.enums.DesiredJob
import com.bamyanggang.domainmodule.domain.user.enums.JobSearchStatus
import com.bamyanggang.domainmodule.domain.user.enums.SocialLoginProvider
import com.bamyanggang.domainmodule.global.entity.AggregateRoot
import com.bamyanggang.supportmodule.uuid.UuidCreator
import java.time.LocalDateTime
import java.util.UUID

data class User (
    override val id: UUID = UuidCreator.create(),
    val socialId: String,
    val profileImgUrl: String,
    val provider: SocialLoginProvider,
    val nickName: String,
    val jobSearchStatus: JobSearchStatus? = null,
    val desiredJob: DesiredJob? = null,
    val goal: String? = null,
    val dream: String? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
) : AggregateRoot{

    companion object {
        fun create(
            socialId: String,
            profileImgUrl: String,
            provider: SocialLoginProvider,
            nickName: String,
            jobSearchStatus: JobSearchStatus?,
            desiredJob: DesiredJob?,
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
            )
        }

        fun toDomain(
            id: UUID,
            socialId: String,
            profileImgUrl: String,
            provider: SocialLoginProvider,
            nickName: String,
            jobSearchStatus: JobSearchStatus?,
            desiredJob: DesiredJob?,
            goal: String?,
            dream: String?
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
                dream = dream
            )
        }
    }
}