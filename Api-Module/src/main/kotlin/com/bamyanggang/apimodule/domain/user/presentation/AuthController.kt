package com.bamyanggang.apimodule.domain.user.presentation

import com.bamyanggang.apimodule.domain.user.application.dto.Logout
import com.bamyanggang.apimodule.domain.user.application.dto.Reissue
import com.bamyanggang.apimodule.domain.user.application.dto.SocialLogin
import com.bamyanggang.apimodule.domain.user.application.service.AuthService
import com.bamyanggang.domainmodule.domain.user.enums.SocialLoginProvider
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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

    @PutMapping(AuthApi.REISSUE)
    fun reissueToken(
        @RequestBody request: Reissue.Request
    ): Reissue.Response = authService.reissueToken(request)

    @DeleteMapping(AuthApi.LOGOUT)
    fun logout(
        @RequestBody request: Logout.Request
    ) {
        authService.logout(request)
    }

}