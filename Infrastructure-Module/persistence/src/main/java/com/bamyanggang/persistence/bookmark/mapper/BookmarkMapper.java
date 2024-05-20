package com.bamyanggang.persistence.bookmark.mapper;

import com.bamyanggang.domainmodule.domain.bookmark.aggregate.Bookmark;
import com.bamyanggang.persistence.bookmark.jpa.entity.BookmarkJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class BookmarkMapper {

    public BookmarkJpaEntity toJpaEntity(Bookmark bookmark) {
        return new BookmarkJpaEntity(
                bookmark.getId(),
                bookmark.getBookmarkStatus(),
                bookmark.getJobDescriptionId(),
                bookmark.getExperienceId()
        );
    }

    public Bookmark toDomainEntity(BookmarkJpaEntity bookmarkJpaEntity) {
        return new Bookmark(
                bookmarkJpaEntity.getBookmarkId(),
                bookmarkJpaEntity.getJobDescriptionId(),
                bookmarkJpaEntity.getExperienceId(),
                bookmarkJpaEntity.getBookmarkStatus()
        );
    }

}
