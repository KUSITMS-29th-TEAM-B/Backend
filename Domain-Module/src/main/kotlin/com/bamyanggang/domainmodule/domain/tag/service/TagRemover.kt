package com.bamyanggang.domainmodule.domain.tag.service

import com.bamyanggang.domainmodule.domain.tag.exception.TagException
import com.bamyanggang.domainmodule.domain.tag.repository.TagRepository
import java.util.*

class TagRemover(
    private val tagRepository: TagRepository
) {
    fun removeTag(tagId: UUID) {
        if (!tagRepository.isExistById(tagId)) {
            throw TagException.NotFoundTag()
        }
        tagRepository.deleteByTagId(tagId)
    }
}
