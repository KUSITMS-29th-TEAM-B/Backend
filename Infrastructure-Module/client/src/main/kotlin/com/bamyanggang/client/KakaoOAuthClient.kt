package com.bamyanggang.client

import com.fasterxml.jackson.annotation.JsonAlias
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class KakaoOAuthClient {

    private val webclient = WebClient.create("https://kapi.kakao.com")

    fun retrieveUserInfo(accessToken: String): KakaoUserInfo? {
        return webclient.get()
            .uri("/v2/user/me")
            .header("Authorization", "Bearer $accessToken")
            .retrieve()
            .bodyToMono(KakaoUserInfo::class.java)
            .block()
    }

    data class KakaoUserInfo(
        val id: String,
        @field:JsonAlias("kakao_account")
        val kakaoAccount: KakaoAccount
    )

    data class KakaoAccount(
        val profile: Map<String, Any?>,
    ) {
        val nickname: String
            get() = profile["nickname"] as String
    }


}