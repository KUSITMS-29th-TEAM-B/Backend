package com.bamyanggang.domainmodule.domain.bookmark.service

import com.bamyanggang.domainmodule.domain.bookmark.aggregate.Bookmark
import com.bamyanggang.domainmodule.domain.bookmark.repository.BookmarkRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookmarkAppender(
    private val bookmarkRepository: BookmarkRepository
) {
    fun appendBookmark(jobDescriptionId: UUID, experienceId: UUID) {
        Bookmark.create(jobDescriptionId, experienceId).also { bookmarkRepository.save(it) }
    }

}
