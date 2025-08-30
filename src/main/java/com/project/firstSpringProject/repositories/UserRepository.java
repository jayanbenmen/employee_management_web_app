package com.project.firstSpringProject.repositories;

import com.project.firstSpringProject.dtos.UserSummary;
import com.project.firstSpringProject.entities.Role;
import com.project.firstSpringProject.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);

    
    @Query("select u.id as id, u.username as username, u.firstName as firstName, u.lastName as lastName, u.email as email from User u")
    List<UserSummary> findUsers();

    @Modifying
    @Transactional
    @Query("delete from User u where u.username like :username")
    void deleteByUsername(@Param("username") String username);
}
