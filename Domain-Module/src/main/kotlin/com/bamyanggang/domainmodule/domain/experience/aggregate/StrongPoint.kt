package com.bamyanggang.domainmodule.domain.experience.aggregate

import com.bamyanggang.domainmodule.common.entity.AggregateRoot
import com.example.uuid.UuidCreator
import java.util.*

data class StrongPoint(
    override val id: UUID = UuidCreator.create(),
    val name : String,
    val userId : UUID?,
) : AggregateRoot{
    companion object {
        fun create(name: String, userId: UUID?): StrongPoint {
            return StrongPoint(UuidCreator.create(), name, userId)
        }

        fun toDomain(id: UUID, name: String, userId: UUID?): StrongPoint {
            return StrongPoint(id, name, userId)
        }
    }
}