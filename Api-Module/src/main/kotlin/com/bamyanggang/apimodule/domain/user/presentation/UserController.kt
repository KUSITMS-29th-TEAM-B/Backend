package com.bamyanggang.apimodule.domain.user.presentation

import com.bamyanggang.apimodule.domain.user.application.dto.ProfileImageResponse
import com.bamyanggang.apimodule.domain.user.application.dto.Register
import com.bamyanggang.apimodule.domain.user.application.dto.UserInfoResponse
import com.bamyanggang.apimodule.domain.user.application.service.ProfileImageGetService
import com.bamyanggang.apimodule.domain.user.application.service.UserCreateService
import com.bamyanggang.apimodule.domain.user.application.service.UserInfoGetService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userCreateService: UserCreateService,
    private val profileImageGetService: ProfileImageGetService,
    private val userInfoGetService: UserInfoGetService
) {

    @PostMapping(UserApi.REGISTER)
    fun register(
        @RequestBody request: Register.Request
    ): Register.Response = userCreateService.createUser(request)

    @GetMapping(UserApi.PROFILE_IMG)
    fun getProfileImage(): ProfileImageResponse = profileImageGetService.getProfileImages()

    @GetMapping(UserApi.USER_INFO)
    fun getUserInfo(): UserInfoResponse = userInfoGetService.getUserInfo()


}