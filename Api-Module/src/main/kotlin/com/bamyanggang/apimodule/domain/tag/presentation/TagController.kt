package com.bamyanggang.apimodule.domain.tag.presentation

import com.bamyanggang.apimodule.domain.tag.application.dto.CreateTag
import com.bamyanggang.apimodule.domain.tag.application.service.TagCreateService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class TagController(
    private val tagCreateService: TagCreateService,
) {
    @PostMapping(TagApi.CREATE_TAG, TagApi.BASE_URL)
    fun createTag( @PathVariable("parentTagId", required = false) parentTagId: UUID?,
                   @RequestBody request: CreateTag.Request,
                  ): CreateTag.Response {
        return when {
            parentTagId == null -> tagCreateService.createParentTag(request)
            else -> tagCreateService.createChildTag(request, parentTagId)
        }
    }
}
