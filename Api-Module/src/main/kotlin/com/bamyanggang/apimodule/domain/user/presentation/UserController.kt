package com.bamyanggang.apimodule.domain.user.presentation

import com.bamyanggang.apimodule.domain.user.application.dto.Register
import com.bamyanggang.apimodule.domain.user.application.dto.UserInfo
import com.bamyanggang.apimodule.domain.user.application.service.UserCreateService
import com.bamyanggang.apimodule.domain.user.application.service.UserInfoGetService
import com.bamyanggang.apimodule.domain.user.application.service.UserInfoUpdateService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userCreateService: UserCreateService,
    private val userInfoGetService: UserInfoGetService,
    private val userInfoUpdateService: UserInfoUpdateService
) {

    @PostMapping(UserApi.REGISTER)
    fun register(
        @RequestBody request: Register.Request
    ): Register.Response = userCreateService.createUser(request)
    @GetMapping(UserApi.USER_INFO)
    fun getUserInfo(): UserInfo.Response.Success = userInfoGetService.getUserInfo()

    @PatchMapping(UserApi.USER_INFO_UPDATE)
    fun updateUserInfo(
        @RequestBody request: UserInfo.Request.UpdateUserInfo
    ) = userInfoUpdateService.updateUserInfo(request)


}
