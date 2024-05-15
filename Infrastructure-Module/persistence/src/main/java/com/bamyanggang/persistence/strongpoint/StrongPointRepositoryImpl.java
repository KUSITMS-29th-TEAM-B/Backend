package com.bamyanggang.persistence.strongpoint;

import com.bamyanggang.domainmodule.domain.experience.repository.StrongPointRepository;
import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.StrongPoint;
import com.bamyanggang.persistence.strongpoint.jpa.entity.StrongPointJpaEntity;
import com.bamyanggang.persistence.strongpoint.jpa.repository.StrongPointJpaRepository;
import com.bamyanggang.persistence.strongpoint.mapper.StrongPointMapper;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StrongPointRepositoryImpl implements StrongPointRepository {
    private final StrongPointJpaRepository strongPointJpaRepository;
    private final StrongPointMapper strongPointMapper;

    @Override
    public UUID save(StrongPoint strongPoint) {
        StrongPointJpaEntity strongPointJpaEntity = strongPointMapper.toJpaEntity(strongPoint);
        strongPointJpaRepository.save(strongPointJpaEntity);

        return strongPointJpaEntity.getId();
    }

    @Override
    public List<StrongPoint> findAllByUserId(UUID userId) {
        List<StrongPointJpaEntity> strongPoints = strongPointJpaRepository.findAllByUserId(userId);

        return strongPoints.stream().map(strongPointEntity ->
                    StrongPoint.Companion.toDomain(
                        strongPointEntity.getId(),
                        strongPointEntity.getName(),
                        strongPointEntity.getUserId()
                    )
            ).toList();
    }
}
