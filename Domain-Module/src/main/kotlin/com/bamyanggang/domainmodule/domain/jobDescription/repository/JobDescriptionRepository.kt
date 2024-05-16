package com.bamyanggang.domainmodule.domain.jobDescription.repository

import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.JobDescription

interface JobDescriptionRepository {

    fun save(jobDescription: JobDescription)

}
