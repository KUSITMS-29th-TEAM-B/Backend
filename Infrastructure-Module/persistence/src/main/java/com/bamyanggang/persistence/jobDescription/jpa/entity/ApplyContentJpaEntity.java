package com.bamyanggang.persistence.jobDescription.jpa.entity;


import com.bamyanggang.persistence.common.UUIDBinaryConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "apply_content")
public class ApplyContentJpaEntity {

    @Id
    @Column(name = "apply_content_id", columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID applyContentId;

    @Column(columnDefinition = "VARCHAR(3000)")
    private String question;

    @Column(columnDefinition = "VARCHAR(3000)")
    private String answer;

    @Column(name = "apply_id", columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID applyId;

    public ApplyContentJpaEntity(UUID applyContentId, String question, String answer, UUID applyId) {
        this.applyContentId = applyContentId;
        this.question = question;
        this.answer = answer;
        this.applyId = applyId;
    }

}
