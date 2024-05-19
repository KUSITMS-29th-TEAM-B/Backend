package com.bamyanggang.apimodule.domain.strongpoint.application.dto

import java.util.*

class CreateStrongPoint {
    data class Request(
        val names: List<String>,
    )

    data class Response(
        val id: UUID,
    )
}
