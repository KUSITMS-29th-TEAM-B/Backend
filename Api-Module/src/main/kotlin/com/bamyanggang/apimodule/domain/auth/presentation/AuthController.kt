package com.bamyanggang.apimodule.domain.auth.presentation

import com.bamyanggang.apimodule.domain.auth.application.dto.SocialLogin
import com.bamyanggang.apimodule.domain.auth.application.service.AuthService
import com.bamyanggang.domainmodule.domain.auth.enum.SocialLoginProvider
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authService: AuthService
) {

    @PostMapping(AuthApi.SOCIAL_LOGIN)
    fun socialLogin(
        @PathVariable provider: SocialLoginProvider,
        @RequestBody request: SocialLogin.Request
    ): SocialLogin.Response = authService.executeSocialLogin(provider,request)
}