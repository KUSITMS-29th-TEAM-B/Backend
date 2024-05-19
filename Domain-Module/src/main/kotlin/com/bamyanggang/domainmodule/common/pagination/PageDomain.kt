package com.bamyanggang.domainmodule.common.pagination

data class PageDomain<T>(
    val content: List<T>,
    val pageNumber: Int,
    val pageSize: Int,
    val totalPage: Int,
    val hasNext: Boolean
)
