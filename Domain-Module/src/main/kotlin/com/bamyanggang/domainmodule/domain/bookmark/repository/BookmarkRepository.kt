package com.bamyanggang.domainmodule.domain.bookmark.repository

import com.bamyanggang.domainmodule.domain.bookmark.aggregate.Bookmark
import com.bamyanggang.domainmodule.domain.bookmark.enums.BookmarkStatus
import java.util.*

interface BookmarkRepository {

    fun findById(jobDescriptionId : UUID, experienceId : UUID) : Bookmark?
    fun save(bookmark: Bookmark)
    fun findByStatusAndJobDescriptionId(jobDescriptionId: UUID, status: BookmarkStatus): List<Bookmark>
    fun findByExperienceIds(experiences: List<UUID>): List<Bookmark>
}
