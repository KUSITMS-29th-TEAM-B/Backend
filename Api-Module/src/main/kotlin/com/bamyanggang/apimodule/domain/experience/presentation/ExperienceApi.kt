package com.bamyanggang.apimodule.domain.experience.presentation

object ExperienceApi {
    const val BASE_URL = "/api/experiences"
    const val EXPERIENCE_PATH_VARIABLE_URL = "$BASE_URL/{experienceId}"
    const val ALL_YEARS = "$BASE_URL/all-years"
    const val BOOKMARK_EXPERIENCE_URL = "$BASE_URL/bookmark/{jobDescriptionId}"
}
