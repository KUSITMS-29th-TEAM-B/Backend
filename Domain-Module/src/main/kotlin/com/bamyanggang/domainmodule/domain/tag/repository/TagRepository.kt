package com.bamyanggang.domainmodule.domain.tag.repository

import com.bamyanggang.domainmodule.domain.tag.aggregate.Tag
import java.util.*

interface TagRepository {
    fun save(newTag : Tag)
    fun findById(id : UUID) : Tag
    fun findAllParentTagsByUserId(userId: UUID): List<Tag>
    fun findAllChildTagsByUserIdAndParentTagId(userId: UUID, parentTagId: UUID): List<Tag>
    fun deleteByTagId(tagId: UUID)
    fun isExistById(tagId: UUID): Boolean
    fun findByParentTagIds(tagParentTagIds: List<UUID>): List<Tag>
    fun findByUserIdAndNameContains(userId: UUID, search: String): List<Tag>
    fun findAllChildTagsByParentTagId(parentTagId: UUID) : List<Tag>
    fun findByParentTagIdsAndYearAndExperienceCreatedAtDesc(parentTagIds: List<UUID>, year: Int): List<Tag>
}
