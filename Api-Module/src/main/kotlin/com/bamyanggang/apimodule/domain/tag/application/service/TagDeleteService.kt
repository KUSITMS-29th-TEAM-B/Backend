package com.bamyanggang.apimodule.domain.tag.application.service

import com.bamyanggang.domainmodule.domain.tag.service.TagRemover
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class TagDeleteService(
    private val tagRemover: TagRemover
) {
    @Transactional
    fun deleteTag(tagId: UUID) {
        tagRemover.removeTag(tagId)
    }
}
