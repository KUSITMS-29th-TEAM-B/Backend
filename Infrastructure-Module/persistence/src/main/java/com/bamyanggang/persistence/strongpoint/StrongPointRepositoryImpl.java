package com.bamyanggang.persistence.strongpoint;

import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.StrongPoint;
import com.bamyanggang.domainmodule.domain.strongpoint.repository.StrongPointRepository;
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
    public void save(StrongPoint strongPoint) {
        StrongPointJpaEntity newStrongPointJpaEntity = strongPointMapper.toJpaEntity(strongPoint);
        strongPointJpaRepository.save(newStrongPointJpaEntity);
    }

    @Override
    public List<StrongPoint> findAllByUserId(UUID userId) {
        List<StrongPointJpaEntity> strongPointJpaEntities = strongPointJpaRepository.findAllByUserId(userId);
        return strongPointJpaEntities.stream().map(strongPointMapper::toDomainEntity).toList();
    }

    @Override
    public void deleteByStrongPointId(UUID strongPointId) {
        strongPointJpaRepository.deleteById(strongPointId);
    }

    @Override
    public boolean isExistByStrongPointId(UUID strongPointId) {
        return strongPointJpaRepository.existsById(strongPointId);
    }

    @Override
    public List<StrongPoint> findByIds(List<UUID> strongPointIds) {
        List<StrongPointJpaEntity> strongPointJpaEntities = strongPointJpaRepository.findByIds(strongPointIds);
        return strongPointJpaEntities.stream().map(strongPointMapper::toDomainEntity).toList();
    }

    @Override
    public void saveAll(List<StrongPoint> strongPoints) {
        List<StrongPointJpaEntity> strongPointJpaEntities = strongPoints.stream()
                .map(strongPointMapper::toJpaEntity).toList();

        strongPointJpaRepository.saveAll(strongPointJpaEntities);
    }

    @Override
    public List<StrongPoint> findByUserIdAndNameContains(UUID userId, String search) {
        List<StrongPointJpaEntity> strongPointJpaEntities = strongPointJpaRepository.findByUserIdAndNameContaining(userId, search);
        return strongPointJpaEntities.stream().map(strongPointMapper::toDomainEntity).toList();
    }
}
