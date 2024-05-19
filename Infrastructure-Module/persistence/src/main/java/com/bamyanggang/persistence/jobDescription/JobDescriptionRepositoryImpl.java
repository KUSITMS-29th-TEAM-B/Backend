package com.bamyanggang.persistence.jobDescription;

import com.bamyanggang.domainmodule.common.pagination.SliceDomain;
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.JobDescription;
import com.bamyanggang.domainmodule.domain.jobDescription.repository.JobDescriptionRepository;
import com.bamyanggang.persistence.jobDescription.jpa.entity.JobDescriptionJpaEntity;
import com.bamyanggang.persistence.jobDescription.jpa.repository.JobDescriptionJpaRepository;
import com.bamyanggang.persistence.jobDescription.mapper.JobDescriptionMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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

    @Override
    public SliceDomain<JobDescription> findAllByUserIdAndSortByCreatedAt(UUID userId, int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Slice<JobDescriptionJpaEntity> jobDescriptionJpaEntitySlice =  jobDescriptionJpaRepository.findAllByUserIdOrderByCreatedAtDesc(userId, pageable);
        Slice<JobDescription> jobDescriptionSlice = jobDescriptionJpaEntitySlice.map(jobDescriptionMapper::toDomainEntity);
        return new SliceDomain<>(jobDescriptionSlice.getContent(), jobDescriptionSlice.getNumber(), jobDescriptionSlice.getSize(), jobDescriptionSlice.hasNext());
    }

    @Override
    public SliceDomain<JobDescription> findAllByUserId(UUID userId, int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Slice<JobDescriptionJpaEntity> jobDescriptionJpaEntitySlice =  jobDescriptionJpaRepository.findAllByUserIdOrderByCreatedAtDesc(userId, pageable);
        Slice<JobDescription> jobDescriptionSlice = jobDescriptionJpaEntitySlice.map(jobDescriptionMapper::toDomainEntity);
        return new SliceDomain<>(jobDescriptionSlice.getContent(), jobDescriptionSlice.getNumber(), jobDescriptionSlice.getSize(), jobDescriptionSlice.hasNext());
    }

//    @Override
//    public Slice<JobDescription> findAllByUserIdAndSortByCreatedAt(UUID userId, Integer page, Integer size) {
//        Pageable pageable = Pageable.ofSize(size).withPage(page);
//        Slice<JobDescriptionJpaEntity> jobDescriptionJpaEntitySlice =  jobDescriptionJpaRepository.findAllByUserIdOrderByCreatedAtDesc(userId, pageable);
//        return jobDescriptionJpaEntitySlice.map(jobDescriptionMapper::toDomainEntity);
//    }
//
//    @Override
//    public Slice<JobDescription> findAllByUserIdAndSortType(UUID userId, Pageable pageable) {
//        Slice<JobDescriptionJpaEntity> jobDescriptionJpaEntitySlice =  jobDescriptionJpaRepository.findAllByUserId(userId, pageable);
//        return jobDescriptionJpaEntitySlice.map(jobDescriptionMapper::toDomainEntity);
//    }

}
