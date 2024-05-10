package com.bamyanggang.apimodule.global.security.filter

import com.bamyanggang.apimodule.domain.user.application.service.TokenService
import com.bamyanggang.apimodule.domain.user.presentation.AuthApi
import com.bamyanggang.apimodule.global.security.JwtAuthenticationToken
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpMethod
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val tokenService: TokenService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (!isIgnoredPath(request)) {
            extractToken(request)?.let { processToken(it) }
        }
        filterChain.doFilter(request, response)
        SecurityContextHolder.clearContext()
    }

    private fun extractToken(request: HttpServletRequest): String? {
        val bearerToken: String? = request.getHeader(TOKEN_HEADER)
        return if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            bearerToken.substring(TOKEN_PREFIX.length)
        } else null
    }

    private fun processToken(token: String) {
        with(tokenService.resolveAccessToken(token)){
            JwtAuthenticationToken(this)
        }.let { securityContext ->
            SecurityContextHolder.getContext().authentication = securityContext
        }
    }


    private fun isIgnoredPath(request: HttpServletRequest): Boolean {
        val antPathMatcher = AntPathMatcher()
        return ignoredPath.any { (ignoredPathURI, method) ->
            val matchesPath = antPathMatcher.matchStart(ignoredPathURI, request.requestURI)
            val matchesMethod = method.matches(request.method)
            matchesPath && matchesMethod
        }
    }

    companion object {
        const val TOKEN_HEADER = "Authorization"
        const val TOKEN_PREFIX = "Bearer "
        private val ignoredPath: Map<String, HttpMethod> = mapOf(
            "/docs/**" to HttpMethod.GET,
            "/favicon.ico" to HttpMethod.GET,
            AuthApi.BASE_URL.plus("/login/**") to HttpMethod.POST
        )
    }

}
