package com.bamyanggang.apimodule.global.config

import com.bamyanggang.apimodule.global.security.filter.JwtAuthenticationFilter
import com.bamyanggang.apimodule.global.security.filter.JwtEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig(private val jwtAuthenticationFilter: JwtAuthenticationFilter,
private val jwtEntryPoint: JwtEntryPoint) {

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity {
            authorizeRequests {
                authorize(anyRequest, permitAll)

            }
            formLogin { disable() }
            httpBasic { disable() }
            logout { disable() }
            sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS }
            csrf { disable() }
            addFilterBefore<UsernamePasswordAuthenticationFilter>(jwtAuthenticationFilter)
            addFilterBefore<JwtAuthenticationFilter>(jwtEntryPoint)
        }
        return httpSecurity.build()
    }
}
