package com.bamyanggang.domainmodule.domain.strongpoint.aggregate

import com.bamyanggang.domainmodule.common.entity.DomainEntity
import com.example.uuid.UuidCreator
import java.util.*

class Keyword(
    override val id: UUID,
    val defaultKeywordId: UUID,
): DomainEntity{

    companion object{
        fun create(defaultKeywordId: UUID): Keyword {
            return Keyword(
                id = UuidCreator.create(),
                defaultKeywordId = defaultKeywordId
            )
        }
    }
}
