package com.bamyanggang.apimodule.common.dto

import com.bamyanggang.domainmodule.common.pagination.SliceDomain

data class SliceResponse<T>(
    val content: List<T>,
    val page: Int,
    val size: Int,
    val hasNext: Boolean
) {
    companion object {
        fun <T> from(slice: SliceDomain<T>): SliceResponse<T> {
            return SliceResponse(
                content = slice.content,
                page = slice.pageNumber,
                size = slice.pageSize,
                hasNext = slice.hasNext
            )
        }
    }
}
