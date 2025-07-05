package com.example.School_Management.repository;

import com.example.School_Management.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section,Long> {
}
