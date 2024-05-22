package com.bamyanggang.apimodule.domain.tag.config

import com.bamyanggang.domainmodule.domain.tag.repository.TagRepository
import com.bamyanggang.domainmodule.domain.tag.service.TagAppender
import com.bamyanggang.domainmodule.domain.tag.service.TagReader
import com.bamyanggang.domainmodule.domain.tag.service.TagRemover
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TagServiceConfiguration(
    private val tagRepository: TagRepository
) {

    @Bean
    fun tagAppender(): TagAppender {
        return TagAppender(tagRepository)
    }

    @Bean
    fun tagReader(): TagReader {
        return TagReader(tagRepository)
    }

    @Bean
    fun tagModifier(): TagRemover {
        return TagRemover(tagRepository)
    }

}
