package com.bamyanggang.persistence.tag;

import com.bamyanggang.domainmodule.domain.tag.aggregate.Tag;
import com.bamyanggang.domainmodule.domain.tag.exception.TagException.NotFoundTag;
import com.bamyanggang.domainmodule.domain.tag.repository.TagRepository;
import com.bamyanggang.persistence.tag.jpa.entity.TagJpaEntity;
import com.bamyanggang.persistence.tag.jpa.repository.TagJpaRepository;
import com.bamyanggang.persistence.tag.mapper.TagMapper;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepository {
    private final TagMapper tagMapper;
    private final TagJpaRepository tagJpaRepository;

    @Override
    public void save(Tag newTag) {
        TagJpaEntity newTagJpaEntity = tagMapper.toJpaEntity(newTag);
        tagJpaRepository.save(newTagJpaEntity);
    }

    @Override
    public List<Tag> findAllParentTagsByUserId(UUID userId) {
        List<TagJpaEntity> parentTagJpaEntities = tagJpaRepository.findAllByUserIdAndParentTagIdIsNull(userId);
        return parentTagJpaEntities.stream().map(tagMapper::toDomainEntity).toList();
    }

    @Override
    public List<Tag> findAllChildTagsByUserId(UUID userId, UUID parentTagId) {
        List<TagJpaEntity> childTagJpaEntities = tagJpaRepository.findAllByUserIdAndParentTagId(userId, parentTagId);
        return childTagJpaEntities.stream().map(tagMapper::toDomainEntity).toList();
    }

    @Override
    public void deleteByTagId(UUID tagId) {
        tagJpaRepository.deleteById(tagId);
    }

    @Override
    public boolean isExistById(UUID tagId) {
        return tagJpaRepository.existsById(tagId);
    }

    @Override
    public List<Tag> findByParentTagIds(List<UUID> parentTagIds) {
        List<TagJpaEntity> tagJpaEntities = tagJpaRepository.findAllById(parentTagIds);
        return tagJpaEntities.stream().map(tagMapper::toDomainEntity).toList();
    }

    @Override
    public Tag findById(UUID tagId) {
        TagJpaEntity tagJpaEntity = tagJpaRepository.findById(tagId).orElseThrow(NotFoundTag::new);
        return tagMapper.toDomainEntity(tagJpaEntity);
    }

    @Override
    public List<Tag> findAllChildTagsByParentTagId(UUID parentTagId) {
        List<TagJpaEntity> tagJpaEntities = tagJpaRepository.findAllByParentTagId(parentTagId);
        return tagJpaEntities.stream().map(tagMapper::toDomainEntity).toList();
    }
}
