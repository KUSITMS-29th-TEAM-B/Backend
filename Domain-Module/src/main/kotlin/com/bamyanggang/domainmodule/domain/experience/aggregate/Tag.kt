package com.bamyanggang.domainmodule.domain.experience.aggregate

import com.bamyanggang.domainmodule.common.entity.DomainEntity
import com.example.uuid.UuidCreator
import java.util.*

data class Tag(
    override val id: UUID = UuidCreator.create(),
    val name : String,
    val parentTag : Tag?
) : DomainEntity{
    companion object {
        fun create(name: String, parentTag: Tag?): Tag {
            return Tag(UuidCreator.create(), name, parentTag)
        }

        fun toDomain(id : UUID, name: String, parentTag: Tag?): Tag {
            return Tag(id, name, parentTag)
        }
    }
}