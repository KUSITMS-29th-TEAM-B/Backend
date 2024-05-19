package com.bamyanggang.domainmodule.domain.jobDescription.repository

import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.Apply
import java.util.*

interface ApplyRepository {
    fun save(apply: Apply)

    fun findByJobDescriptionId(jobDescriptionId: UUID): Apply?

}
