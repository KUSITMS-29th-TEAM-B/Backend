package com.bamyanggang.apimodule.global.security.filter

import com.bamyanggang.commonmodule.exception.CustomException
import com.bamyanggang.commonmodule.exception.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtEntryPoint : OncePerRequestFilter() {

    private val objectMapper = ObjectMapper()

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            filterChain.doFilter(request, response)
        } catch (exception: CustomException) {
            handleException(response, exception)
        }
    }

    private fun handleException(response: HttpServletResponse, exception: CustomException) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"
        response.writer.write(objectMapper.writeValueAsString(ErrorResponse(exception.code, exception.message)))
    }
}

