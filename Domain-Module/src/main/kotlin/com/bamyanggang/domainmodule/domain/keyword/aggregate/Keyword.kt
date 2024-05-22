package com.bamyanggang.domainmodule.domain.keyword.aggregate

import com.bamyanggang.domainmodule.common.entity.AggregateRoot
import com.example.uuid.UuidCreator
import java.util.*

class Keyword(
    override val id: UUID,
    val defaultKeywordId: UUID,
    val userId: UUID
): AggregateRoot{

    companion object{
        fun create(defaultKeywordId: UUID, userId: UUID): Keyword{
            return Keyword(
                id = UuidCreator.create(),
                defaultKeywordId = defaultKeywordId,
                userId = userId
            )
        }
    }
}
