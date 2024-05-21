package com.bamyanggang.domainmodule.domain.bookmark.service

import com.bamyanggang.domainmodule.domain.bookmark.aggregate.Bookmark
import com.bamyanggang.domainmodule.domain.bookmark.enums.BookmarkStatus
import com.bamyanggang.domainmodule.domain.bookmark.repository.BookmarkRepository
import java.util.*

class BookmarkReader(
    private val bookmarkRepository: BookmarkRepository
) {

    fun readBookmark(jobDescriptionId: UUID, experienceId: UUID) : Bookmark? {
        return bookmarkRepository.findById(jobDescriptionId, experienceId)
    }

    fun readByStatusAndJobDescriptionId(jobDescriptionId: UUID, status: BookmarkStatus): List<Bookmark> {
        return bookmarkRepository.findByStatusAndJobDescriptionId(jobDescriptionId, status)
    }

    fun readByExperienceIds(experiencesIds: List<UUID>) : List<Bookmark>{
        return bookmarkRepository.findByExperienceIds(experiencesIds)
    }
}
