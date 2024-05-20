package com.bamyanggang.domainmodule.domain.bookmark.aggregate

import com.bamyanggang.domainmodule.common.entity.AggregateRoot
import com.bamyanggang.domainmodule.domain.bookmark.enums.BookmarkStatus
import java.util.UUID

data class Bookmark(
    override val id: UUID = UUID.randomUUID(),
    val jobDescriptionId: UUID,
    val experienceId:UUID,
    val bookmarkStatus: BookmarkStatus
): AggregateRoot {

    fun changeBookmarkStatus(): Bookmark {
        return when (bookmarkStatus) {
            BookmarkStatus.ON -> copy(bookmarkStatus = BookmarkStatus.OFF)
            BookmarkStatus.OFF -> copy(bookmarkStatus = BookmarkStatus.ON)
        }
    }

    companion object {
        fun create(
            jobDescriptionId: UUID,
            experienceId: UUID
        ): Bookmark {
            return Bookmark(
                jobDescriptionId = jobDescriptionId,
                experienceId = experienceId,
                bookmarkStatus = BookmarkStatus.ON
            )
        }
    }

}
