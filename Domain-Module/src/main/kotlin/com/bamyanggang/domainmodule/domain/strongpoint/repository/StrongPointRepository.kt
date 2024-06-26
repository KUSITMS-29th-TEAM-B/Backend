package com.bamyanggang.domainmodule.domain.strongpoint.repository

import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.StrongPoint
import java.util.*

interface StrongPointRepository {
    fun save(newStrongPoint: StrongPoint)
    fun findAllByUserId(userId: UUID): List<StrongPoint>
    fun deleteByStrongPointId(strongPointId: UUID)
    fun isExistByStrongPointId(strongPointId: UUID): Boolean
    fun findByIds(strongPointIds: List<UUID>) : List<StrongPoint>
    fun saveAll(strongPoints: List<StrongPoint>)
    fun findByUserIdAndNameContains(userId: UUID, search: String): List<StrongPoint>
}
