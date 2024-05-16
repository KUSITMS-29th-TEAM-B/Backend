package com.bamyanggang.domainmodule.domain.strongpoint.repository

import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.StrongPoint
import java.util.*

interface StrongPointRepository {
    fun save(newStrongPoint: StrongPoint): UUID

    fun findAllByUserId(userId: UUID): List<StrongPoint>
}
