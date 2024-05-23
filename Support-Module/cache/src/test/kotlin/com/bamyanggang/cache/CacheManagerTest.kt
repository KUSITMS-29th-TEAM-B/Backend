package com.bamyanggang.cache

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CacheManagerTest {

    @Test
    fun testCacheByKey() {
        // Given
        val key = "testKey"
        val body = {
            "body"
        }
        // When
        val result = CacheManager.cacheByKey(key, body = body)

        // Then
        assertEquals("body", result)
    }

    @Test
    fun testCacheByKeyWithSameKey() {
        // Given
        val key = "testKey"
        val body = {
            "body"
        }
        // When
        val result1 = CacheManager.cacheByKey(key, body = body)
        val result2 = CacheManager.cacheByKey(key, body = body)

        // Then
        assertEquals("body", result1)
        assertEquals("body", result2)
    }

}
