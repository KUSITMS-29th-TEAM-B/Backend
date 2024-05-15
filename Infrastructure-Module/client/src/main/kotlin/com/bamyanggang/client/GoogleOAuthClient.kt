package com.bamyanggang.client

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class GoogleOAuthClient {

    private val webClient = WebClient.create("https://www.googleapis.com")

    fun retrieveUserInfo(accessToken: String): GoogleUserInfo? {
        return webClient.get()
            .uri("/oauth2/v3/userinfo")
            .header("Authorization", "Bearer $accessToken")
            .attribute("access_token", accessToken)
            .retrieve()
            .bodyToMono(GoogleUserInfo::class.java)
            .block()
    }
    
    data class GoogleUserInfo(
        val id: String,
        val name: String
    )
}