package com.bamyanggang.persistence.strongpoint.mapper;

import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.StrongPoint;
import com.bamyanggang.persistence.strongpoint.jpa.entity.StrongPointJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class StrongPointMapper {
    public StrongPointJpaEntity toJpaEntity(StrongPoint strongPoint) {
        return new StrongPointJpaEntity(
                strongPoint.getId(),
                strongPoint.getName(),
                strongPoint.getUserId()
        );
    }

    public StrongPoint toDomainEntity(StrongPointJpaEntity strongPointJpaEntity) {
        return new StrongPoint(
                strongPointJpaEntity.getStrongPointId(),
                strongPointJpaEntity.getName(),
                strongPointJpaEntity.getUserId()
        );
    }
}
