package com.bamyanggang.domainmodule.domain.tag.service

import com.bamyanggang.domainmodule.domain.tag.repository.TagRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class TagRemover(
    private val tagRepository: TagRepository
) {
    fun removeTag(tagId: UUID) {
        tagRepository.deleteByTagId(tagId)
    }
}
