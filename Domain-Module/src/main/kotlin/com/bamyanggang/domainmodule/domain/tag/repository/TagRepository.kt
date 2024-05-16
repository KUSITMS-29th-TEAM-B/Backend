package com.bamyanggang.domainmodule.domain.tag.repository

import com.bamyanggang.domainmodule.domain.tag.aggregate.Tag
import java.util.*

interface TagRepository {
    fun save(newTag : Tag): UUID
    fun findAllParentTagsByUserId(userId: UUID): List<Tag>
    fun findAllChildTagsByUserId(userId: UUID, parentId: UUID): List<Tag>
}
