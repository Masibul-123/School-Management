package com.gmmps.repository;

import com.gmmps.entity.SectionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionInfoRepository extends JpaRepository<SectionInfo,Long> {
}
