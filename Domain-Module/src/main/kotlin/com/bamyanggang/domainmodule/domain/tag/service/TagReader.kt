package com.bamyanggang.domainmodule.domain.tag.service

import com.bamyanggang.domainmodule.domain.tag.aggregate.Tag
import com.bamyanggang.domainmodule.domain.tag.repository.TagRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class TagReader(
    private val tagRepository: TagRepository
) {
    fun readAllParentTagsByUserId(userId: UUID): List<Tag> {
        return tagRepository.findAllParentTagsByUserId(userId)
    }

    fun readAllChildTagsByUserId(userId: UUID, parentId: UUID): List<Tag> {
        return tagRepository.findAllChildTagsByUserId(userId, parentId)
    }

    fun readById(tagId: UUID): Tag {
        return tagRepository.findById(tagId)
    }
}
