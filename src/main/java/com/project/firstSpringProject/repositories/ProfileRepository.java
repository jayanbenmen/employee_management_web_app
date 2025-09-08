package com.project.firstSpringProject.repositories;

import com.project.firstSpringProject.dtos.ProfileSummary;
import com.project.firstSpringProject.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Profile findById(int id);
    Profile findByNfcLike(String nfc);

    @Query("select p.id as id, " +
            "p.duration as duration, " +
            "p.hourlyRate as hourlyRate, " +
            "p.nfc as nfc, " +
            "u.username as username, " +
            "j.name as jobName, " +
            "s.name as shiftName " +
            "from Profile p join p.user u " +
            "join p.jobTitle j " +
            "join p.shift s " +
            "where p.id = :id")
    ProfileSummary findProfileById(@Param("id") int id);

    @Query("select p.id as id, " +
            "p.duration as duration, " +
            "p.hourlyRate as hourlyRate, " +
            "p.nfc as nfc, " +
            "u.username as username, " +
            "j.name as jobName, " +
            "s.name as shiftName " +
            "from Profile p join p.user u " +
            "join p.jobTitle j " +
            "join p.shift s " +
            "where u.username = :username")
    ProfileSummary findProfileByUsername(@Param("username") String username);

    @Query("select p from Profile p join p.user where p.user.id = :user_id")
    Profile findProfileByUserId(@Param("user_id") int user_id);
}
