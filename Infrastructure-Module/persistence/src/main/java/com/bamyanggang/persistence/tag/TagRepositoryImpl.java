package com.bamyanggang.persistence.tag;

import com.bamyanggang.domainmodule.domain.tag.aggregate.Tag;
import com.bamyanggang.domainmodule.domain.tag.repository.TagRepository;
import com.bamyanggang.persistence.tag.jpa.entity.TagJpaEntity;
import com.bamyanggang.persistence.tag.jpa.repository.TagJpaRepository;
import com.bamyanggang.persistence.tag.mapper.TagMapper;
import jakarta.transaction.Transactional;
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
    @Transactional
    public UUID save(Tag newTag) {
        TagJpaEntity newTagJpaEntity = tagMapper.toJpaEntity(newTag);
        tagJpaRepository.save(newTagJpaEntity);

        return newTagJpaEntity.getTagId();
    }

    @Override
    @Transactional
    public List<Tag> findAllParentTagsByUserId(UUID userId) {
        List<TagJpaEntity> parentTagJpaEntities = tagJpaRepository.findAllByUserIdAndParentTagIdIsNull(userId);
        return parentTagJpaEntities.stream().map(tagMapper::toDomainEntity).toList();
    }

    @Override
    @Transactional
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
}
