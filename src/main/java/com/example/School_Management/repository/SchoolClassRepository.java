package com.example.School_Management.repository;

import com.example.School_Management.entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass,Long> {
}
