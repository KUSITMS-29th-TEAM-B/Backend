package com.bamyanggang.domainmodule.domain.user.service

import com.bamyanggang.domainmodule.domain.user.aggregate.vo.ProfileImage
import com.bamyanggang.domainmodule.domain.user.repository.ProfileImageRepository
import org.springframework.stereotype.Service

@Service
class ProfileImageReader(
    private val profileImageRepository: ProfileImageRepository
) {
    fun readProfileImages() : List<ProfileImage> {
        return profileImageRepository.findAll()
    }
}