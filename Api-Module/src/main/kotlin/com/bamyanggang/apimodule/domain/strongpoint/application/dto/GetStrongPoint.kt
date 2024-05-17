package com.bamyanggang.apimodule.domain.strongpoint.application.dto

import java.util.*

class GetStrongPoint {
    data class Response(
        val count: Int,
        val strongPoints: List<StrongPointInfo>
    )

    data class StrongPointInfo(
        val id : UUID,
        val name : String,
    )
}
