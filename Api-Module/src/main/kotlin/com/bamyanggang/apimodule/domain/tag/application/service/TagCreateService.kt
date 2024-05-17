package com.bamyanggang.apimodule.domain.tag.application.service

import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.apimodule.domain.tag.application.dto.CreateTag
import com.bamyanggang.domainmodule.domain.tag.aggregate.Tag
import com.bamyanggang.domainmodule.domain.tag.exception.TagException
import com.bamyanggang.domainmodule.domain.tag.service.TagAppender
import com.bamyanggang.domainmodule.domain.tag.service.TagReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class TagCreateService(
    private val tagAppender: TagAppender,
    private val tagReader: TagReader,
) {
    @Transactional
    fun createChildTag(request: CreateTag.Request, parentTagId: UUID): CreateTag.Response {
        return getAuthenticationPrincipal()
            .also {
                val userChildTags = tagReader.readAllChildTagsByUserId(it, parentTagId)
                validateTagCountLimit(userChildTags.size)
                validateDuplicatedName(userChildTags, request.name)
            }.let {
                val newChildTagId = tagAppender.appendChildTag(request.name, parentTagId, it)
                CreateTag.Response(newChildTagId)
            }
    }

    @Transactional
    fun createParentTag(request: CreateTag.Request): CreateTag.Response {
        return getAuthenticationPrincipal()
            .also {
                val userParentTags = tagReader.readAllParentTagsByUserId(it)
                validateTagCountLimit(userParentTags.size)
                validateDuplicatedName(userParentTags, request.name)
            }.let {
                val newParentTagId = tagAppender.appendParentTag(request.name, it)
                CreateTag.Response(newParentTagId)
            }
    }

    private fun validateDuplicatedName(userParentTags: List<Tag>, name: String) {
        userParentTags.forEach {
            if (it.isDuplicatedName(name)) {
                throw TagException.DuplicatedTagName()
            }
        }
    }

    private fun validateTagCountLimit(size: Int) {
        val limit = 10
        if (size > limit) {
            throw TagException.OverTagCountLimit()
        }
    }
}
