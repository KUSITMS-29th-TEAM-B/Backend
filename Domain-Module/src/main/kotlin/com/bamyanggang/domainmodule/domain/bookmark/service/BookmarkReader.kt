package com.bamyanggang.domainmodule.domain.bookmark.service

import com.bamyanggang.domainmodule.domain.bookmark.aggregate.Bookmark
import com.bamyanggang.domainmodule.domain.bookmark.enums.BookmarkStatus
import com.bamyanggang.domainmodule.domain.bookmark.repository.BookmarkRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookmarkReader(
    private val bookmarkRepository: BookmarkRepository
) {

    fun readBookmark(jobDescriptionId: UUID, experienceId: UUID) : Bookmark? {
        return bookmarkRepository.findByIds(jobDescriptionId, experienceId)
    }

    fun readByStatusAndJobDescriptionId(jobDescriptionId: UUID, status: BookmarkStatus): List<Bookmark> {
        return bookmarkRepository.findByStatusAndJobDescriptionId(jobDescriptionId, status)
    }

}
