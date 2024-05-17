package com.bamyanggang.apimodule.domain.tag.presentation

import com.bamyanggang.apimodule.domain.tag.application.dto.CreateTag
import com.bamyanggang.apimodule.domain.tag.application.service.TagCreateService
import com.bamyanggang.apimodule.domain.tag.application.service.TagDeleteService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class TagController(
    private val tagCreateService: TagCreateService,
    private val tagDeleteService: TagDeleteService
) {
    @PostMapping(TagApi.BASE_URL, TagApi.TAG_PATH_VARIABLE_URL)
    fun createTag(
        @PathVariable("tagId", required = false) parentTagId: UUID?,
        @RequestBody request: CreateTag.Request,
    ): CreateTag.Response {
        return when {
            parentTagId == null -> tagCreateService.createParentTag(request)
            else -> tagCreateService.createChildTag(request, parentTagId)
        }
    }

    @DeleteMapping(TagApi.TAG_PATH_VARIABLE_URL)
    fun deleteTag( @PathVariable("tagId", required = false) tagId: UUID) {
        tagDeleteService.deleteTag(tagId)
    }
}
