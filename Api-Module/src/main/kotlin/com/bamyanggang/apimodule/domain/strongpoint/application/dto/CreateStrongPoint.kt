package com.bamyanggang.apimodule.domain.strongpoint.application.dto

import java.util.*

class CreateStrongPoint {
    data class Request(
        val names: List<StrongPointName>,
    )

    data class Response(
        val strongPoints: List<DetailStrongPoint>,
    )

    data class StrongPointName(
        val name: String
    )

    data class DetailStrongPoint(
        val id : UUID,
        val name : String,
    )
}
