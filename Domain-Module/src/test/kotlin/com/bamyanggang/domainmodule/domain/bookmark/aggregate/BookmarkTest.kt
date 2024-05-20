package com.bamyanggang.domainmodule.domain.bookmark.aggregate

import com.bamyanggang.commonmodule.fixture.generateFixture
import com.bamyanggang.domainmodule.domain.bookmark.enums.BookmarkStatus
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.*

class BookmarkTest : FunSpec({
    test("북마크 생성") {
        // arrange
        val jobDescriptionId = UUID.randomUUID()
        val experienceId = UUID.randomUUID()

        // act
        val bookmark: Bookmark = generateFixture {
            it.set("jobDescriptionId", jobDescriptionId)
            it.set("experienceId", experienceId)
        }

        // assert
        bookmark.jobDescriptionId shouldBe jobDescriptionId
        bookmark.experienceId shouldBe experienceId
    }

    test("북마크 상태 변경") {
        // arrange
        val jobDescriptionId = UUID.randomUUID()
        val experienceId = UUID.randomUUID()
        val bookmark: Bookmark = generateFixture {
            it.set("jobDescriptionId", jobDescriptionId)
            it.set("experienceId", experienceId)
            it.set("bookmarkStatus", BookmarkStatus.ON)
        }

        // act
        val changedBookmark = bookmark.changeBookmarkStatus()

        // assert
        changedBookmark.bookmarkStatus shouldBe BookmarkStatus.OFF
    }

})
