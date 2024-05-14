package com.bamyanggang.domainmodule.domain.experience.aggregate

import com.bamyanggang.domainmodule.common.entity.DomainEntity
import com.example.uuid.UuidCreator
import java.util.*

data class Tag(
    override val id: UUID = UuidCreator.create(),
    val name: String,
    val parentTagId: UUID?,
    val userId: UUID
) : DomainEntity{
    companion object {
        fun create(name: String, parentTagId: UUID, userId: UUID): Tag {
            return Tag(UuidCreator.create(), name, parentTagId, userId)
        }

        fun toDomain(id : UUID, name: String, parentTagId: UUID, userId: UUID): Tag {
            return Tag(id, name, parentTagId, userId)
        }
    }
}