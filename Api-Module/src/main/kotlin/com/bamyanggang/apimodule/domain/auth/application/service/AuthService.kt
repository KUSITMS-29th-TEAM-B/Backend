package com.bamyanggang.apimodule.domain.auth.application.service

import com.bamyanggang.apimodule.domain.auth.application.dto.SocialLogin
import com.bamyanggang.apimodule.domain.auth.application.service.command.AuthInvoker
import com.bamyanggang.domainmodule.domain.auth.enum.SocialLoginProvider
import org.springframework.stereotype.Service

@Service
class AuthService(private val authInvoker: AuthInvoker){
        fun executeSocialLogin(provider: SocialLoginProvider, request: SocialLogin.Request): SocialLogin.Response {
            return authInvoker.invoke(request, provider)
        }
}