package com.bamyanggang.domainmodule.domain.strongpoint.service

import com.bamyanggang.domainmodule.domain.strongpoint.repository.KeywordRepository
import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.StrongPoint
import java.util.*

class KeywordReader(
    private val keywordRepository: KeywordRepository
) {
    fun readByIds(strongPointIds: List<UUID>): List<StrongPoint> {
        keywordRepository.findByIds(strongPointIds)
    }

}
