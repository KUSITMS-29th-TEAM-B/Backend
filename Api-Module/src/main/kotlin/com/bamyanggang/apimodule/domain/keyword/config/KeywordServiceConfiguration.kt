package com.bamyanggang.apimodule.domain.keyword.config

import com.bamyanggang.domainmodule.domain.keyword.repository.KeywordRepository
import com.bamyanggang.domainmodule.domain.keyword.service.KeywordReader
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KeywordServiceConfiguration (
    private val keywordRepository: KeywordRepository
) {
    @Bean
    fun keywordReader() : KeywordReader {
        return KeywordReader(keywordRepository)
    }
}
