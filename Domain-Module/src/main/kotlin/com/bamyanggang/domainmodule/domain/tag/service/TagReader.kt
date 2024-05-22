package com.bamyanggang.domainmodule.domain.tag.service

import com.bamyanggang.domainmodule.domain.tag.aggregate.Tag
import com.bamyanggang.domainmodule.domain.tag.repository.TagRepository
import java.util.*

class TagReader(
    private val tagRepository: TagRepository
) {
    fun readAllParentTagsByUserId(userId: UUID): List<Tag> {
        return tagRepository.findAllParentTagsByUserId(userId)
    }

    fun readAllChildTagsByUserIdAndParentTagId(userId: UUID, parentId: UUID): List<Tag> {
        return tagRepository.findAllChildTagsByUserIdAndParentTagId(userId, parentId)
    }

    fun readById(tagId: UUID): Tag {
        return tagRepository.findById(tagId)
    }
    
    fun readByIds(parentTagIds: List<UUID>): List<Tag> {
        return tagRepository.findByParentTagIds(parentTagIds)
    }

    fun readIdsByUserIdAndNameContains(userId: UUID, search: String) : List<UUID> {
        return tagRepository.findByUserIdAndNameContains(userId, search).map { it.id }
    }
    
    fun readChildTagsByParentTagId(parentTagId: UUID) : List<Tag> {
        return tagRepository.findAllChildTagsByParentTagId(parentTagId)
    }
}
