package com.project.firstSpringProject.repositories;

import com.project.firstSpringProject.dtos.JobTitleSummary;
import com.project.firstSpringProject.entities.JobTitle;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, Integer> {
    Optional<JobTitle> findByNameLike(String name);
    JobTitle findByName(String name);
    JobTitle findById(int id);

    @Query("select j.id as id, j.name as name, d.name as departmentName from JobTitle j join j.department d where j.id = :id")
    JobTitleSummary findJobTitleById(@Param("id") int id);


    @Query("select j.id as id, j.name as name, d.name as departmentName from JobTitle j join j.department d where j.name = :name")
    JobTitleSummary findJobTitleByName(@Param("name") String name);

    @Query("select j.id as id, j.name as name, d.name as departmentName from JobTitle j join j.department d")
    List<JobTitleSummary> findJobs();

    @Modifying
    @Transactional
    @Query("delete from JobTitle j where j.name like :name")
    void deleteByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query("delete from JobTitle j where j.id = :id")
    void deleteById(int id);
}
