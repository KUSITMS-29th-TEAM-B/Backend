package com.bamyanggang.domainmodule.domain.bookmark.service

import com.bamyanggang.domainmodule.domain.bookmark.repository.BookmarkRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class BookmarkAppenderTest: BehaviorSpec({
    val mockBookmarkRepository = mockk<BookmarkRepository>(relaxed = true)
    val bookmarkAppender = BookmarkAppender(mockBookmarkRepository)

    given("BookmarkAppender의 appendBookmark 메소드를 테스트한다") {
        val jobDescriptionId = UUID.randomUUID()
        val experienceId = UUID.randomUUID()
        `when`("북마크를 추가하면") {
            bookmarkAppender.appendBookmark(jobDescriptionId, experienceId)
            then("북마크가 저장된다") {
                verify(exactly = 1) {
                    mockBookmarkRepository.save(any())
                }
            }
        }
    }

})
