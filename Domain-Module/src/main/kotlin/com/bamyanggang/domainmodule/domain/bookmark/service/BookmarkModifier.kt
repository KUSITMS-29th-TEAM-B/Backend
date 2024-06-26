package com.bamyanggang.domainmodule.domain.bookmark.service

import com.bamyanggang.domainmodule.domain.bookmark.aggregate.Bookmark
import com.bamyanggang.domainmodule.domain.bookmark.repository.BookmarkRepository


class BookmarkModifier(
    private val bookmarkRepository: BookmarkRepository
) {
    fun modifyBookmarkStatus(bookmark: Bookmark) {
        bookmark.changeBookmarkStatus().also { bookmarkRepository.save(it) }
    }

}
