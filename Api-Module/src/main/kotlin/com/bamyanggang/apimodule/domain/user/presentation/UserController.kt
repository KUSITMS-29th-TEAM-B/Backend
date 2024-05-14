package com.bamyanggang.apimodule.domain.user.presentation

import com.bamyanggang.apimodule.domain.user.application.dto.Register
import com.bamyanggang.apimodule.domain.user.application.service.UserCreateService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userCreateService: UserCreateService
) {

    @PostMapping(UserApi.REGISTER)
    fun register(
        @RequestBody request: Register.Request
    ): Register.Response = userCreateService.createUser(request)
}