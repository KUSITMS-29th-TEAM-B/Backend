package com.bamyanggang.domainmodule.domain.tag.aggregate

import com.bamyanggang.domainmodule.common.entity.DomainEntity
import com.example.uuid.UuidCreator
import java.util.*

data class Tag(
    override val id: UUID = UuidCreator.create(),
    val name: String,
    val parentTagId: UUID?,
    val userId: UUID
) : DomainEntity{

    fun isDuplicatedName(name: String): Boolean = this.name == name

    companion object {
        fun create(name: String, parentTagId: UUID?, userId: UUID): Tag {
            return Tag(
                name = name,
                parentTagId = parentTagId,
                userId = userId
            )
        }
    }
}
