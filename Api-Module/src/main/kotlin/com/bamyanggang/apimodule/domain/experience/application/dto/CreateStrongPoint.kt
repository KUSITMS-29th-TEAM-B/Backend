package com.bamyanggang.apimodule.domain.experience.application.dto

import java.util.*

class CreateStrongPoint {
    data class Request(
        val name: String,
    )

    data class Response(
        val id: UUID,
    )
}