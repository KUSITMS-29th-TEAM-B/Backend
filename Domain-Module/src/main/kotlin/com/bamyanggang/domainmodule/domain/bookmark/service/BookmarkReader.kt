package com.bamyanggang.domainmodule.domain.bookmark.service

import com.bamyanggang.domainmodule.domain.bookmark.aggregate.Bookmark
import com.bamyanggang.domainmodule.domain.bookmark.repository.BookmarkRepository
import java.util.*

class BookmarkReader(
    private val bookmarkRepository: BookmarkRepository
) {

    fun readBookmark(jobDescriptionId: UUID, experienceId: UUID) : Bookmark? {
        return bookmarkRepository.findByIds(jobDescriptionId, experienceId)
    }

}
