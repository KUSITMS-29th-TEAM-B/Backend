package com.bamyanggang.apimodule.domain.bookmark.application

import com.bamyanggang.domainmodule.domain.bookmark.aggregate.Bookmark
import com.bamyanggang.domainmodule.domain.bookmark.service.BookmarkAppender
import com.bamyanggang.domainmodule.domain.bookmark.service.BookmarkModifier
import com.bamyanggang.domainmodule.domain.bookmark.service.BookmarkReader
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class BookmarkServiceTest: BehaviorSpec({
    val mockBookmarkReader = mockk<BookmarkReader>(relaxed = true)
    val mockBookmarkModifier = mockk<BookmarkModifier>(relaxed = true)
    val mockBookmarkAppender = mockk<BookmarkAppender>(relaxed = true)
    val service = BookmarkService(mockBookmarkReader, mockBookmarkModifier, mockBookmarkAppender)

    given("BookmarkService.bookmark") {
        val jobDescriptionId = UUID.randomUUID()
        val experienceId = UUID.randomUUID()

        `when`("readBookmark가 null을 반환하면") {
            every { mockBookmarkReader.readBookmark(jobDescriptionId, experienceId) } returns null
            service.bookmark(jobDescriptionId, experienceId)
            then("appendBookmark가 호출된다.") {
                verify {
                    mockBookmarkAppender.appendBookmark(jobDescriptionId, experienceId)
                }
            }
        }

        `when`("readBookmark가 null이 아닌 값을 반환하면") {
            val bookmark = mockk<Bookmark>()
            every { mockBookmarkReader.readBookmark(jobDescriptionId, experienceId) } returns bookmark
            service.bookmark(jobDescriptionId, experienceId)
            then("modifyBookmarkStatus가 호출된다.") {
                verify {
                    mockBookmarkModifier.modifyBookmarkStatus(bookmark)
                }
            }
        }
    }

})
