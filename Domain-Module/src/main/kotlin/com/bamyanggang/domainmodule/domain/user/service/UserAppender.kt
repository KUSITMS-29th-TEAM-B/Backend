package com.bamyanggang.domainmodule.domain.user.service

import com.bamyanggang.domainmodule.domain.user.aggregate.User
import com.bamyanggang.domainmodule.domain.user.enums.SocialLoginProvider
import com.bamyanggang.domainmodule.domain.user.exception.UserException
import com.bamyanggang.domainmodule.domain.user.repository.UserRepository

class UserAppender(
    private val userRepository: UserRepository
) {
    fun appendUser (
        socialId: String,
        profileImgUrl: String,
        provider: SocialLoginProvider,
        email: String,
        nickName: String,
        jobSearchStatus: String?,
        desiredJob: String?,
        goal: String?,
        dream: String?
    ): User {
        if(userRepository.existsBySocialId(socialId)) {throw UserException.DuplicatedUser()}
        return User.create(
            socialId = socialId,
            profileImgUrl = profileImgUrl,
            provider = provider,
            email = email,
            nickName = nickName,
            jobSearchStatus = jobSearchStatus,
            desiredJob = desiredJob,
            goal = goal,
            dream = dream
        ).also { userRepository.save(it) }
    }
}
