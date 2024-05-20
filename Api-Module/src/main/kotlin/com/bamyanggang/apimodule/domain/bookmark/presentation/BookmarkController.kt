package com.bamyanggang.apimodule.domain.bookmark.presentation

import com.bamyanggang.apimodule.domain.bookmark.application.BookmarkService
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class BookmarkController(
    private val bookmarkService: BookmarkService
) {

    @PatchMapping(BookmarkApi.BOOKMARK)
    fun bookmark(
        @PathVariable("jobDescriptionId") jobDescriptionId: UUID,
        @PathVariable("experienceId") experienceId: UUID
    ) {
        bookmarkService.bookmark(jobDescriptionId, experienceId)
    }

}
