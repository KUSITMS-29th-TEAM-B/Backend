package com.bamyanggang.apimodule.domain.tag.application.service

import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.domainmodule.domain.experience.service.ExperienceReader
import com.bamyanggang.domainmodule.domain.experience.service.ExperienceRemover
import com.bamyanggang.domainmodule.domain.tag.service.TagReader
import com.bamyanggang.domainmodule.domain.tag.service.TagRemover
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class TagDeleteService(
    private val tagRemover: TagRemover,
    private val tagReader: TagReader,
    private val experienceReader: ExperienceReader,
    private val experienceRemover: ExperienceRemover
) {
    @Transactional
    fun deleteTag(tagId: UUID) {
        val deleteTag = tagReader.readById(tagId)

        if (deleteTag.parentTagId == null) {
            experienceReader.readByUserIdAndParentTagId(getAuthenticationPrincipal(), deleteTag.id)
                .forEach { experienceRemover.remove(it.id) }
        }else {
            experienceReader.readByChildTagId(tagId).forEach {
                experienceRemover.remove(it.id)
            }
        }

        tagRemover.removeTag(tagId)
    }
}
