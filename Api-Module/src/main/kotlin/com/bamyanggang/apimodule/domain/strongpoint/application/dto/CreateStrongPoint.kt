package com.bamyanggang.apimodule.domain.strongpoint.application.dto

class CreateStrongPoint {
    data class Request(
        val names: List<StrongPointName>,
    )

    data class Response(
        val strongPoints: List<GetStrongPoint.DetailStrongPoint>,
    )

    data class StrongPointName(
        val name: String
    )
}
