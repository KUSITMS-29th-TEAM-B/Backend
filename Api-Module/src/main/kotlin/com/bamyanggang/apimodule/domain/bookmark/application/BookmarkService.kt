package com.bamyanggang.apimodule.domain.bookmark.application

import com.bamyanggang.domainmodule.domain.bookmark.service.BookmarkAppender
import com.bamyanggang.domainmodule.domain.bookmark.service.BookmarkModifier
import com.bamyanggang.domainmodule.domain.bookmark.service.BookmarkReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class BookmarkService(
    private val bookmarkReader: BookmarkReader,
    private val bookmarkModifier: BookmarkModifier,
    private val bookmarkAppender: BookmarkAppender
) {

    @Transactional
    fun bookmark(jobDescriptionId: UUID, experienceId: UUID) {
        bookmarkReader.readBookmark(jobDescriptionId, experienceId)?.let {
            bookmarkModifier.modifyBookmarkStatus(it)
        } ?: bookmarkAppender.appendBookmark(jobDescriptionId, experienceId)
    }
}
