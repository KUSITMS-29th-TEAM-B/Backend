package com.bamyanggang.cache

import java.util.concurrent.TimeUnit
import com.github.benmanes.caffeine.cache.Caffeine


class CacheManager {
    companion object{
        private val cache = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build<String, Any>()


        fun <R> cacheByKey(key: String,  body: () -> R): R {
            cache.getIfPresent(key)?.let {
                return it as R
            } ?: run {
                val result = body()
                cache.put(key, result)
                return result
            }
        }
    }
}