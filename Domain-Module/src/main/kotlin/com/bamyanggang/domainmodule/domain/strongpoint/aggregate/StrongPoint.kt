package com.bamyanggang.domainmodule.domain.strongpoint.aggregate

import com.bamyanggang.domainmodule.common.entity.DomainEntity
import com.bamyanggang.domainmodule.domain.strongpoint.exception.StrongPointExceptionMessage
import com.example.uuid.UuidCreator
import java.util.*

data class StrongPoint(
    override val id: UUID = UuidCreator.create(),
    val name : String,
    val userId : UUID,
) : DomainEntity{
    init {
        require(name.isNotEmpty()) {StrongPointExceptionMessage.NAME_NOT_EMPTY.message}
    }

    fun isDuplicated(name: String) : Boolean = this.name == name

    companion object {
        const val LIMIT = 10
      
        fun create(name: String, userId: UUID): StrongPoint {
            return StrongPoint(name = name, userId = userId)
        }

        fun toDomain(id: UUID, name: String, userId: UUID): StrongPoint {
            return StrongPoint(id, name, userId)
        }
    }
}
