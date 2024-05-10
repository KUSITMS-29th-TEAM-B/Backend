package com.bamyanggang.infrastructuremodule.persistence.domain.strongpoint.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "strong_point")
public class StrongPointJpaEntity {
    @Id
    private UUID id;

    private String name;
}
