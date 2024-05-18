package com.bamyanggang.domainmodule.domain.strongpoint.repository

import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.StrongPoint
import java.util.*

interface StrongPointRepository {
    fun save(newStrongPoint: StrongPoint)

    fun findAllByUserId(userId: UUID): List<StrongPoint>
}
