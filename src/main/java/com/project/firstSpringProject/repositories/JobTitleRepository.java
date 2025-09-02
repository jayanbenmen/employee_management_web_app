package com.project.firstSpringProject.repositories;

import com.project.firstSpringProject.entities.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, Integer> {
    Optional<JobTitle> findByNameLike(String name);
}
