package com.bamyanggang.apimodule.common

import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

fun getAuthenticationPrincipal(): UUID {
    return SecurityContextHolder.getContext().authentication.principal as UUID
}