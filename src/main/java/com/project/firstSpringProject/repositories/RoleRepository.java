package com.project.firstSpringProject.repositories;

import com.project.firstSpringProject.dtos.RoleSummary;
import com.project.firstSpringProject.entities.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT r from User u JOIN u.roles r WHERE u.username = :username")
    Role findRoleByUsername(@Param("username") String username);

    Role findByNameLike(String name);

    Optional<Role> findByName(String name);

    @Modifying
    @Transactional
    @Query("delete from Role r where r.name like :name")
    void deleteByName(@Param("name") String name);

    @Query("select r.id as id, r.name as name from Role r")
    List<RoleSummary> findRoles();
}
