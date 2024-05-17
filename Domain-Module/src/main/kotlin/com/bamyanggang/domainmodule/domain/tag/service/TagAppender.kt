package com.bamyanggang.domainmodule.domain.tag.service

import com.bamyanggang.domainmodule.domain.tag.aggregate.Tag
import com.bamyanggang.domainmodule.domain.tag.repository.TagRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class TagAppender(
    private val tagRepository: TagRepository
) {
    fun appendParentTag(name: String, userId: UUID): UUID {
        return Tag.create(name, null, userId)
            .let { tagRepository.save(it) }
    }

    fun appendChildTag(name: String, parentTagId: UUID, userId: UUID): UUID {
        return Tag.create(name, parentTagId, userId)
            .let { tagRepository.save(it)
        }
    }
}
