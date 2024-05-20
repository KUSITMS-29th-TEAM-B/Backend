package com.bamyanggang.domainmodule.domain.bookmark.service

import io.kotest.matchers.shouldBe
import com.bamyanggang.domainmodule.domain.bookmark.aggregate.Bookmark
import com.bamyanggang.domainmodule.domain.bookmark.repository.BookmarkRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import java.util.*

class BookmarkReaderTest: BehaviorSpec({
    val mockBookmarkRepository = mockk<BookmarkRepository>()
    val bookmarkReader = BookmarkReader(mockBookmarkRepository)

    given("BookmarkReader의 readBookmark 메소드를 테스트한다") {
        val jobDescriptionId = UUID.randomUUID()
        val experienceId = UUID.randomUUID()
        val expectedBookmark = mockk<Bookmark>()
        every { mockBookmarkRepository.findByIds(jobDescriptionId, experienceId) } returns expectedBookmark

        `when`("북마크를 조회하면") {
            val result = bookmarkReader.readBookmark(jobDescriptionId, experienceId)
            then("예상한 북마크가 반환된다") {
                result shouldBe expectedBookmark
            }
        }
    }

})
