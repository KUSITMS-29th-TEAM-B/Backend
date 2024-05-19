package com.bamyanggang.apimodule.common.dto

import com.bamyanggang.domainmodule.common.pagination.PageDomain

data class PageResponse<T>(
    val content: List<T>,
    val page: Int,
    val size: Int,
    val totalPage: Int,
    val hasNext: Boolean
) {
    companion object {
        fun <T> from(page: PageDomain<T>): PageResponse<T> {
            return PageResponse(
                content = page.content,
                page = page.pageNumber,
                size = page.pageSize,
                totalPage = page.totalPage,
                hasNext = page.hasNext
            )
        }
    }
}
