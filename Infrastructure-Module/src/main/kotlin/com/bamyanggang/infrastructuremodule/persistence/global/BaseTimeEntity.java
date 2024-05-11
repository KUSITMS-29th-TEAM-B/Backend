package com.bamyanggang.infrastructuremodule.persistence.global;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
@MappedSuperclass
public class BaseTimeEntity {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
