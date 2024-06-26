package com.bamyanggang.apimodule.domain.tag.presentation

import com.bamyanggang.apimodule.domain.tag.application.dto.CreateTag
import com.bamyanggang.apimodule.domain.tag.application.dto.GetChildTag
import com.bamyanggang.apimodule.domain.tag.application.dto.GetParentTag
import com.bamyanggang.apimodule.domain.tag.application.dto.GetTag
import com.bamyanggang.apimodule.domain.tag.application.service.TagCreateService
import com.bamyanggang.apimodule.domain.tag.application.service.TagDeleteService
import com.bamyanggang.apimodule.domain.tag.application.service.TagGetService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class TagController(
    private val tagCreateService: TagCreateService,
    private val tagDeleteService: TagDeleteService,
    private val tagGetService: TagGetService
) {
    @GetMapping(TagApi.ALL_YEARS)
    fun getAllYearsByParentTag(@PathVariable("parentTagId") parentTagId: UUID): GetParentTag.Years{
        return tagGetService.getAllYearsByParentTagId(parentTagId)
    }

    @GetMapping(TagApi.TOP_RANK_TAG_URL)
    fun getTopRankTagsByLimit(
        @RequestParam("year") year: Int,
        @RequestParam("limit") limit: Int
    ): GetParentTag.Response  {
        return tagGetService.getParentTagsByYearAndLimit(year, limit)
    }

    @GetMapping(TagApi.MY_PARENT_TAG_URL)
    fun getUserParentTags(): GetParentTag.Response {
        return tagGetService.getAllParentTagByUserId()
    }

    @GetMapping(TagApi.MY_CHILD_TAG_URL)
    fun getUserChildTags(@PathVariable("tagId") parentTagId: UUID): GetChildTag.Response {
        return tagGetService.getAllChildTagsByParentTagId(parentTagId)
    }

    @GetMapping(TagApi.ALL_TAGS)
    fun getAllTags(): GetTag.Response {
        return tagGetService.getAllTags()
    }

    @GetMapping(TagApi.BASE_URL)
    fun getParentTagsByYear(@RequestParam("year") year: Int): GetParentTag.TotalTagInfo {
        return tagGetService.getAllParentTagsByYear(year)
    }

    @GetMapping(TagApi.TAG_PATH_VARIABLE_URL)
    fun getChildTagsByYear(@PathVariable("tagId") parentTagId: UUID,
                        @RequestParam("year") year: Int): GetChildTag.TotalTagInfo {
        return tagGetService.getAllChildTagsByYearAndParentTagId(year, parentTagId)
    }

    @PostMapping(TagApi.BASE_URL)
    fun createParentTag(@RequestBody request: CreateTag.Request): CreateTag.Response {
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
    fun deleteTag( @PathVariable("tagId") tagId: UUID) {
        tagDeleteService.deleteTag(tagId)
    }
}
