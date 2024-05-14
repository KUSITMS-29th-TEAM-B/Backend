package com.bamyanggang.domainmodule.domain.user.repository

import com.bamyanggang.domainmodule.domain.user.aggregate.vo.ProfileImage

interface ProfileImageRepository {
    fun findAll(): List<ProfileImage>

}