package com.bamyanggang.domainmodule.domain.strongpoint.aggregate

import com.bamyanggang.domainmodule.common.entity.DomainEntity
import java.util.*

class Keyword(
    override val id: UUID,
    val name: String,
): DomainEntity
