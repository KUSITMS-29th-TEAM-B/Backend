package com.bamyanggang.domainmodule.domain.user.service

import com.bamyanggang.domainmodule.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserModifier(
    private val userRepository: UserRepository
) {
    fun modifyUserInfo(
        userId: UUID,
        nickName: String?,
        profileImgUrl: String?,
        jobSearchStatus: String?,
        desiredJob: String?,
        goal: String?,
        dream: String?
    ) {
        userRepository
            .findById(userId)
            .update(
                nickName = nickName,
                profileImgUrl = profileImgUrl,
                jobSearchStatus = jobSearchStatus,
                desiredJob = desiredJob,
                goal = goal,
                dream = dream)
            .also { userRepository.save(it) }
    }

}