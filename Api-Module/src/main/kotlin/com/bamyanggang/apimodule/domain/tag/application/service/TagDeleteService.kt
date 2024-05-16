package com.bamyanggang.apimodule.domain.tag.application.service

import com.bamyanggang.domainmodule.domain.tag.service.TagRemover
import org.springframework.stereotype.Service
import java.util.*

@Service
class TagDeleteService(
    private val tagRemover: TagRemover
) {
    fun deleteTag(tagId: UUID) {
        tagRemover.removeTag(tagId)
    }
}
