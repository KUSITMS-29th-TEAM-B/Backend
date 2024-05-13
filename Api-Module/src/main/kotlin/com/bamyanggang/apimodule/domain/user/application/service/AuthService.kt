package com.bamyanggang.apimodule.domain.user.application.service

import com.bamyanggang.apimodule.domain.user.application.dto.SocialLogin
import com.bamyanggang.apimodule.domain.user.application.service.command.AuthInvoker
import com.bamyanggang.domainmodule.domain.user.enums.SocialLoginProvider
import org.springframework.stereotype.Service

@Service
class AuthService(private val authInvoker: AuthInvoker){
        fun executeSocialLogin(provider: SocialLoginProvider, request: SocialLogin.Request): SocialLogin.Response {
            return authInvoker.invoke(request, provider)
        }
}