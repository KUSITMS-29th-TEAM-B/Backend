package com.bamyanggang.domainmodule.domain.bookmark.service

import com.bamyanggang.domainmodule.domain.bookmark.aggregate.Bookmark
import com.bamyanggang.domainmodule.domain.bookmark.repository.BookmarkRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class BookmarkModifierTest: BehaviorSpec({
    val mockBookmarkRepository = mockk<BookmarkRepository>(relaxed = true)
    val bookmarkModifier = BookmarkModifier(mockBookmarkRepository)

    given("BookmarkModifier의 modifyBookmarkStatus 메소드를 테스트한다") {
        val bookmark = mockk<Bookmark>()
        every { bookmark.changeBookmarkStatus() } returns bookmark

        `when`("북마크 상태를 변경하면") {
            bookmarkModifier.modifyBookmarkStatus(bookmark)
            then("북마크가 저장된다") {
                verify(exactly = 1) {
                    mockBookmarkRepository.save(bookmark)
                }
            }
        }
    }

})
