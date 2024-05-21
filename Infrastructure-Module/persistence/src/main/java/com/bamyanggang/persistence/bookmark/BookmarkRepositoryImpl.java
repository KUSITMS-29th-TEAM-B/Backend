package com.bamyanggang.persistence.bookmark;

import com.bamyanggang.domainmodule.domain.bookmark.aggregate.Bookmark;
import com.bamyanggang.domainmodule.domain.bookmark.enums.BookmarkStatus;
import com.bamyanggang.domainmodule.domain.bookmark.repository.BookmarkRepository;
import com.bamyanggang.persistence.bookmark.jpa.entity.BookmarkJpaEntity;
import com.bamyanggang.persistence.bookmark.jpa.repository.BookmarkJpaRepository;
import com.bamyanggang.persistence.bookmark.mapper.BookmarkMapper;
import com.bamyanggang.persistence.experience.mapper.ExperienceMapper;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepository {
    private final BookmarkJpaRepository bookmarkJpaRepository;
    private final BookmarkMapper bookmarkMapper;
    private final ExperienceMapper experienceMapper;

    @Override
    public Bookmark findById(UUID jobDescriptionId, UUID experienceId) {
        return bookmarkJpaRepository.findByJobDescriptionIdAndExperienceId(jobDescriptionId, experienceId)
                .map(bookmarkMapper::toDomainEntity)
                .orElse(null);
    }

    @Override
    public void save(Bookmark bookmark) {
        BookmarkJpaEntity bookmarkJpaEntity = bookmarkMapper.toJpaEntity(bookmark);
        bookmarkJpaRepository.save(bookmarkJpaEntity);
    }

    @Override
    public List<Bookmark> findByStatusAndJobDescriptionId(UUID jobDescriptionId, BookmarkStatus status) {
        List<BookmarkJpaEntity> bookmarkJpaEntities = bookmarkJpaRepository
                .findByBookmarkStatusAndJobDescriptionId(status, jobDescriptionId);

        return bookmarkJpaEntities.stream().map(bookmarkMapper::toDomainEntity).toList();
    }
}
