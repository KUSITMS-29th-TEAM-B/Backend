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
    @PostMapping(TagApi.BASE_URL)
    fun createParentTag(@RequestBody request: CreateTag.Request, ): CreateTag.Response {
        return tagCreateService.createParentTag(request)
    }

    @PostMapping(TagApi.TAG_PATH_VARIABLE_URL)
    fun createChildTag(
        @RequestBody request: CreateTag.Request,
        @PathVariable("tagId") parentTagId : UUID
    ): CreateTag.Response {
        return tagCreateService.createChildTag(request, parentTagId)
    }

    @DeleteMapping(TagApi.TAG_PATH_VARIABLE_URL)
    fun deleteTag( @PathVariable("tagId", required = false) tagId: UUID) {
        tagDeleteService.deleteTag(tagId)
    }
}
