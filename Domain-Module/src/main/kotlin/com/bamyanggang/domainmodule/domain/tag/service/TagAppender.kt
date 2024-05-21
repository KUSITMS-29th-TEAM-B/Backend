package com.bamyanggang.domainmodule.domain.tag.service

import com.bamyanggang.domainmodule.domain.tag.aggregate.Tag
import com.bamyanggang.domainmodule.domain.tag.repository.TagRepository
import java.util.*

class TagAppender(
    private val tagRepository: TagRepository
) {
    fun appendParentTag(name: String, userId: UUID) : Tag {
        return Tag.create(name, null, userId)
            .also { tagRepository.save(it) }
    }

    fun appendChildTag(name: String, parentTagId: UUID, userId: UUID): Tag {
        return Tag.create(name, parentTagId, userId)
            .also { tagRepository.save(it)
        }
    }
}
