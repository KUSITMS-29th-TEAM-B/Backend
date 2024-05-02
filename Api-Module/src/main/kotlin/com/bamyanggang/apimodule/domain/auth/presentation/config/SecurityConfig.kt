package com.bamyanggang.apimodule.domain.auth.presentation.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain


@EnableWebSecurity
@Configuration
class SecurityConfig {
    // TODO: api 추가될 때 white list url 확인해서 추가하기.
    private val whiteList: Array<String> = arrayOf(
        "/api/**",
        "/api-docs/**",
        "/",
        "/error"
    )

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity -> web.ignoring().requestMatchers(*whiteList) }
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { obj: AbstractHttpConfigurer<*, *> -> obj.disable() }
            .formLogin { obj: AbstractHttpConfigurer<*, *> -> obj.disable() }
            .httpBasic { obj: AbstractHttpConfigurer<*, *> -> obj.disable() }
            .sessionManagement { sessionManagementConfigurer ->
                sessionManagementConfigurer
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests { authorizationManagerRequestMatcherRegistry ->
                authorizationManagerRequestMatcherRegistry
                    .anyRequest()
                    .authenticated()
            }
            .build()
    }
}