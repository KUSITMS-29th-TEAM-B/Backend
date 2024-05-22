package com.bamyanggang.domainmodule.domain.strongpoint.repository

import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.Keyword
import java.util.*

interface KeywordRepository {
    fun findByIds(strongPointIds: List<UUID>) : List<Keyword>
}
