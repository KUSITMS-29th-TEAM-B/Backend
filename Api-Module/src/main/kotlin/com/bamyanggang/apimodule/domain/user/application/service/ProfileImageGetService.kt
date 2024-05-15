package com.bamyanggang.apimodule.domain.user.application.service

import com.bamyanggang.apimodule.domain.user.application.dto.ProfileImage
import com.bamyanggang.domainmodule.domain.user.service.ProfileImageReader
import org.springframework.stereotype.Service

@Service
class ProfileImageGetService(
    private val profileImageReader: ProfileImageReader
) {

    fun getProfileImages(): ProfileImage.Response {
        return ProfileImage.Response(
            profileImgUrl = profileImageReader.readProfileImages().map { it.url }
        )
    }
}