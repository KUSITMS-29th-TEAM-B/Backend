package com.bamyanggang.apimodule.domain.strongpoint.config

import com.bamyanggang.domainmodule.domain.strongpoint.repository.KeywordRepository
import com.bamyanggang.domainmodule.domain.strongpoint.service.KeywordReader
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
