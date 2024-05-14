package com.bamyanggang.persistence.user

import com.bamyanggang.domainmodule.domain.user.aggregate.vo.ProfileImage
import com.bamyanggang.domainmodule.domain.user.repository.ProfileImageRepository
import com.bamyanggang.persistence.user.jpa.repository.ProfileImageJpaRepository
import org.springframework.stereotype.Repository

@Repository
class ProfileImageRepositoryImpl(
    private val profileImageJpaRepository: ProfileImageJpaRepository
): ProfileImageRepository {
    override fun findAll(): List<ProfileImage> {
        return profileImageJpaRepository.findAll().map {
            ProfileImage(
                url = it.imgUrl
            )
        }
    }
}