package com.bamyanggang.domainmodule.domain.bookmark.config

import com.bamyanggang.domainmodule.domain.bookmark.repository.BookmarkRepository
import com.bamyanggang.domainmodule.domain.bookmark.service.BookmarkAppender
import com.bamyanggang.domainmodule.domain.bookmark.service.BookmarkModifier
import com.bamyanggang.domainmodule.domain.bookmark.service.BookmarkReader
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BookmarkServiceConfiguration(
    private val bookmarkRepository: BookmarkRepository
) {

    @Bean
    fun bookmarkAppender(): BookmarkAppender {
        return BookmarkAppender(bookmarkRepository)
    }

    @Bean
    fun bookmarkModifier(): BookmarkModifier {
        return BookmarkModifier(bookmarkRepository)
    }

    @Bean
    fun bookmarkReader(): BookmarkReader {
        return BookmarkReader(bookmarkRepository)
    }

}
