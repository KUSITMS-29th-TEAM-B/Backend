package com.bamyanggang.apimodule.domain.tag.presentation

object TagApi {
    const val BASE_URL = "/api/tags"
    const val ALL_TAGS = "${BASE_URL}/all-tags"
    const val MY_PARENT_TAG_URL = "$BASE_URL/my"
    const val TOP_RANK_TAG_URL = "$BASE_URL/top-rank"
    const val TAG_PATH_VARIABLE_URL = "$BASE_URL/{tagId}"
    const val MY_CHILD_TAG_URL = "$BASE_URL/{tagId}/my"
    const val ALL_YEARS = "$BASE_URL/{parentTagId}/all-years"
}
