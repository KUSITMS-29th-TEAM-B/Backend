package com.bamyanggang.jwt

import org.springframework.stereotype.Component

@Component
class DefaultJwtRegistry: JwtRegistry {
    private val jwtMap = mutableMapOf<Any, String>()

    override fun upsert(keyValue: Pair<Any, String>) {
        jwtMap[keyValue.first] = keyValue.second
    }

    override fun isExists(value: String): Boolean {
        return jwtMap.containsValue(value)
    }

    override fun delete(value: String) {
        jwtMap.entries.removeIf { it.value == value }
    }
}