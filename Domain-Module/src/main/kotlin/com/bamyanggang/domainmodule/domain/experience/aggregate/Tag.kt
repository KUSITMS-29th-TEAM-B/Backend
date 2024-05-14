package com.bamyanggang.domainmodule.domain.experience.aggregate

import com.bamyanggang.domainmodule.common.entity.DomainEntity
import com.example.uuid.UuidCreator
import java.util.*

data class Tag(
    override val id: UUID = UuidCreator.create(),
    val name : String,
    val parentTagId : UUID?
) : DomainEntity{
    companion object {
        fun create(name: String, parentTagId: UUID): Tag {
            return Tag(UuidCreator.create(), name, parentTagId)
        }

        fun toDomain(id : UUID, name: String, parentTagId: UUID): Tag {
            return Tag(id, name, parentTagId)
        }
    }
}