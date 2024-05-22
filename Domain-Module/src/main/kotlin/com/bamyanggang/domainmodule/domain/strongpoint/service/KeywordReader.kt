package com.bamyanggang.domainmodule.domain.strongpoint.service

import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.Keyword
import com.bamyanggang.domainmodule.domain.strongpoint.repository.KeywordRepository
import java.util.*

class KeywordReader(
    private val keywordRepository: KeywordRepository
) {
    fun readByIds(strongPointIds: List<UUID>): List<Keyword> {
        return keywordRepository.findByIds(strongPointIds)
    }
}
