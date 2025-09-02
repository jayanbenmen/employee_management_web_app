package com.project.firstSpringProject.repositories;

import com.project.firstSpringProject.dtos.DepartmentSummary;
import com.project.firstSpringProject.entities.Department;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Department findByNameLike(String name);

    @Query("select d.id as id, d.name as name from Department d")
    List<DepartmentSummary> findDepartments();

    @Modifying
    @Transactional
    @Query("delete from Department d where d.name like :name")
    void deleteByName(@Param("name") String name);
}
