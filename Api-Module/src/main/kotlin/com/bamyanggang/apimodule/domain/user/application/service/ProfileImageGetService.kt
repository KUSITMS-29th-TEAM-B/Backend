package com.bamyanggang.apimodule.domain.user.application.service

import com.bamyanggang.apimodule.domain.user.application.dto.ProfileImageResponse
import com.bamyanggang.domainmodule.domain.user.service.ProfileImageReader
import org.springframework.stereotype.Service

@Service
class ProfileImageGetService(
    private val profileImageReader: ProfileImageReader
) {

    fun getProfileImages(): ProfileImageResponse {
        return ProfileImageResponse(
            profileImgUrl = profileImageReader.readProfileImages().map { it.url }
        )
    }
}