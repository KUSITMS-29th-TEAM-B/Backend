package com.bamyanggang.apimodule.domain.jobDescription.presentation

object JobDescriptionApi {
    const val BASE_URL = "/api/job-description"
    const val APPLY = "${BASE_URL}/apply/{jobDescriptionId}"
    const val DETAIL = "${BASE_URL}/{jobDescriptionId}"
    const val STATUS = "${BASE_URL}/status/{jobDescriptionId}"
}
