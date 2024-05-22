package com.bamyanggang.domainmodule.domain.strongpoint.repository

import java.util.*

interface KeywordRepository {
    fun findByIds(strongPointIds: List<UUID>)
}
