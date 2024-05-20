package com.bamyanggang.persistence.jobDescription;

import com.bamyanggang.domainmodule.common.pagination.PageDomain;
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.JobDescription;
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus;
import com.bamyanggang.domainmodule.domain.jobDescription.repository.JobDescriptionRepository;
import com.bamyanggang.persistence.common.exception.PersistenceException;
import com.bamyanggang.persistence.jobDescription.jpa.entity.JobDescriptionJpaEntity;
import com.bamyanggang.persistence.jobDescription.jpa.repository.JobDescriptionJpaRepository;
import com.bamyanggang.persistence.jobDescription.mapper.JobDescriptionMapper;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JobDescriptionRepositoryImpl implements JobDescriptionRepository {
    private final JobDescriptionJpaRepository jobDescriptionJpaRepository;
    private final JobDescriptionMapper jobDescriptionMapper;

    @Override
    public void save(JobDescription jobDescription) {
        JobDescriptionJpaEntity jobDescriptionJpaEntity = jobDescriptionMapper.toJpaEntity(jobDescription);
        jobDescriptionJpaRepository.save(jobDescriptionJpaEntity);
    }

    public JobDescription findById(UUID jobDescriptionId) {
        return jobDescriptionJpaRepository.findById(jobDescriptionId)
                .map(jobDescriptionMapper::toDomainEntity)
                .orElseThrow(() -> new PersistenceException.NotFound());
    }

    @Override
    public PageDomain<JobDescription> findAllByUserIdAndSortByCreatedAt(UUID userId, int page, int size, WriteStatus writeStatus) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("createdAt")));
        Page<JobDescriptionJpaEntity> jobDescriptionJpaEntities = jobDescriptionJpaRepository.findAllByUserIdAndWriteStatus(userId, writeStatus, pageable);
        Page<JobDescription> jobDescriptionSlice = jobDescriptionJpaEntities.map(jobDescriptionMapper::toDomainEntity);
        return new PageDomain<>(jobDescriptionSlice.getContent(), jobDescriptionSlice.getNumber(), jobDescriptionSlice.getSize()
            ,jobDescriptionSlice.getTotalPages(), jobDescriptionSlice.hasNext());
    }

    @Override
    public PageDomain<JobDescription> findAllByUserId(UUID userId, int page, int size, WriteStatus writeStatus) {
        Pageable pageable = PageRequest.of(page, size);
        Page<JobDescriptionJpaEntity> jobDescriptionJpaEntities = jobDescriptionJpaRepository.findAllByUserIdAndWriteStatus(userId, writeStatus, pageable);
        Page<JobDescription> jobDescriptionSlice = jobDescriptionJpaEntities.map(jobDescriptionMapper::toDomainEntity);
        return new PageDomain<>(jobDescriptionSlice.getContent(), jobDescriptionSlice.getNumber(), jobDescriptionSlice.getSize()
                ,jobDescriptionSlice.getTotalPages(), jobDescriptionSlice.hasNext());
    }

    @Override
    public PageDomain<JobDescription> findAllByUserIdAndSortByEndedAt(UUID userId, int page, int size, WriteStatus writeStatus) {
        Pageable pageable = PageRequest.of(page, size);
        LocalDateTime now = LocalDateTime.now();
        Page<JobDescriptionJpaEntity> jobDescriptionJpaEntities = jobDescriptionJpaRepository.findAllByUserIdAndWriteStatusAndTime(userId, writeStatus, now, pageable);
        Page<JobDescription> jobDescriptionSlice = jobDescriptionJpaEntities.map(jobDescriptionMapper::toDomainEntity);
        return new PageDomain<>(jobDescriptionSlice.getContent(), jobDescriptionSlice.getNumber(), jobDescriptionSlice.getSize()
            ,jobDescriptionSlice.getTotalPages(), jobDescriptionSlice.hasNext());
    }

    @Override
    public void deleteById(UUID jobDescriptionId) {
        jobDescriptionJpaRepository.deleteById(jobDescriptionId);
    }

}
