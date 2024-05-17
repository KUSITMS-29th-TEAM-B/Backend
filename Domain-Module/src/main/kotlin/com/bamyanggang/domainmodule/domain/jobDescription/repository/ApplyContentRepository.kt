package com.bamyanggang.domainmodule.domain.jobDescription.repository

import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.ApplyContent

interface ApplyContentRepository {
    fun save(applyContent: ApplyContent)

}
