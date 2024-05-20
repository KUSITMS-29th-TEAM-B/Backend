package com.bamyanggang.domainmodule.domain.bookmark.repository

import com.bamyanggang.domainmodule.domain.bookmark.aggregate.Bookmark
import java.util.*

interface BookmarkRepository {

    fun findByIds(jobDescriptionId : UUID, experienceId : UUID) : Bookmark?

    fun save(bookmark: Bookmark)

}
