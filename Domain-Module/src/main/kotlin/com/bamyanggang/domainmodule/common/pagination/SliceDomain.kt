package com.bamyanggang.domainmodule.common.pagination

data class SliceDomain<T>(
    val content: List<T>,
    val pageNumber: Int,
    val pageSize: Int,
    val hasNext: Boolean
)
