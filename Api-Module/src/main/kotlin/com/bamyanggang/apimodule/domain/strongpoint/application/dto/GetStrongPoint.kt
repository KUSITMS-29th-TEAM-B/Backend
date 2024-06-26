package com.bamyanggang.apimodule.domain.strongpoint.application.dto

import java.util.*

class GetStrongPoint {
    data class Response(
        val count: Int,
        val strongPoints: List<DetailStrongPoint>
    )

    data class DetailStrongPoint(
        val id : UUID,
        val name : String,
    )
}
