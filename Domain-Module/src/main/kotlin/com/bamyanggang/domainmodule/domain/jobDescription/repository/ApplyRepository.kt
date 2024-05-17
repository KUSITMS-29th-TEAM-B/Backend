package com.bamyanggang.domainmodule.domain.jobDescription.repository

import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.Apply

interface ApplyRepository {
    fun save(apply: Apply)

}
