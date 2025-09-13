package com.project.firstSpringProject.controllers;

import com.project.firstSpringProject.dtos.*;
import com.project.firstSpringProject.entities.Department;
import com.project.firstSpringProject.entities.Role;
import com.project.firstSpringProject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/createDepartment")
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentRegistrationDTO departmentRegistrationDTO){
        if(adminService.createDepartment(departmentRegistrationDTO)){
            return ResponseEntity.ok("Department created successfully");
        }
        return  ResponseEntity.badRequest().body("Department already exists");
    }

    @PostMapping("/createRole")
    public ResponseEntity<?> createRole(@RequestBody RoleRegistrationDTO roleRegistrationDTO){
        if(adminService.createRole(roleRegistrationDTO)){
            return ResponseEntity.ok("Role created successfully");
        }
        return ResponseEntity.badRequest().body("Role already exists");
    }

    @PostMapping("/createShift")
    public Object createShift(@RequestBody ShiftCreationDTO shiftCreationDTO){
        return adminService.createShift(shiftCreationDTO);
    }

    @PostMapping("/createProfile/{id}")
    public Object createProfile(@PathVariable int id, @RequestBody ProfileCreationDTO profileCreationDTO){
        return adminService.createProfile(id, profileCreationDTO);
    }

    @PostMapping("/department/{id}/createJob")
    public ResponseEntity<?> createJob(@PathVariable int id, @RequestBody JobTitleRegistrationDTO jobTitleRegistrationDTO){
        if(adminService.createJob(id, jobTitleRegistrationDTO).equals(true)){
            return ResponseEntity.ok("Job Title created successfully");
        }
        return  ResponseEntity.badRequest().body(adminService.createJob(id, jobTitleRegistrationDTO));
    }

    @DeleteMapping("/deleteDepartment/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable int id){
        if(adminService.deleteDepartment(id)){
            return ResponseEntity.ok("Department deleted successfully");
        }
        return ResponseEntity.badRequest().body("Department does not exist");
    }

    @DeleteMapping("/deleteJobTitle/{id}")
    public ResponseEntity<?> deleteJobTitle(@PathVariable int id){
        if(adminService.deleteJobTitle(id)){
            return ResponseEntity.ok("Job Title deleted successfully");
        }
        return ResponseEntity.badRequest().body("Job Title does not exist");
    }

    @DeleteMapping("/deleteRole/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable int id){
        if(adminService.deleteRole(id)){
            return ResponseEntity.ok("Role deleted successfully");
        }
        return ResponseEntity.badRequest().body("Role does not exist");
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        if(adminService.deleteUser(id)){
            return ResponseEntity.ok("User deleted successfully");
        }
        return ResponseEntity.badRequest().body("User does not exist");
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(adminService.getUsers());
    }

    @GetMapping("/departments")
    public ResponseEntity<?> getDepartments(){
        return  ResponseEntity.ok(adminService.getDepartments());
    }

    @GetMapping("/roles")
    public  ResponseEntity<?> getRoles(){
        return  ResponseEntity.ok(adminService.getRoles());
    }

    @GetMapping("/shifts")
    public ResponseEntity<?> getShifts(){
        return ResponseEntity.ok(adminService.getShifts());
    }

    @GetMapping("/jobs")
    public ResponseEntity<?> getJobs(){
        return ResponseEntity.ok(adminService.getJobs());
    }

    @GetMapping("/searchProfile")
    public Object searchProfile(@RequestParam String username){
        return adminService.searchProfile(username);
    }

    @GetMapping("/searchDepartment")
    public ResponseEntity<?> searchDepartment(@RequestParam String name){
        if(adminService.searchDepartment(name) != null){
            return ResponseEntity.ok(adminService.searchDepartment(name));
        }
        return ResponseEntity.badRequest().body("Department does not exist");
    }

    @GetMapping("/searchUser")
    public ResponseEntity<?> searchUser(@RequestParam String username){
        if(adminService.searchUser(username) != null){
            return ResponseEntity.ok(adminService.searchUser(username));
        }
        return ResponseEntity.badRequest().body("User does not exist");
    }

    @GetMapping("/searchRole")
    public ResponseEntity<?> searchRole(@RequestParam String name){
        if(adminService.searchRole(name) != null){
            return ResponseEntity.ok(adminService.searchRole(name));
        }
        return ResponseEntity.badRequest().body("Role does not exist");
    }

    @GetMapping("/searchJob")
    public  ResponseEntity<?> searchJob(@RequestParam String name){
        if(adminService.searchJobTitle(name) != null){
            return ResponseEntity.ok(adminService.searchJobTitle(name));
        }
        return  ResponseEntity.badRequest().body("Job title does not exist");
    }

    @GetMapping("/attendance")
    public Object viewShiftAttendance(
            @RequestParam(required = false) String shiftName,
            @RequestParam(required = false) LocalDate dayDate,
            @RequestParam(required = false) String departmentName,
            @RequestParam(required = false) String jobName
            )
    {

        if(dayDate == null){
            if(shiftName != null) return adminService.viewShiftAttendance(shiftName);
            if(departmentName != null) return adminService.viewDepartmentAttendance(departmentName);
            if(jobName != null) return  adminService.viewJobTitleAttendance(jobName);
            return adminService.viewAllAttendance();
        }

        else{
            if(shiftName != null) return adminService.viewShiftDateAttendance(shiftName, dayDate);
            if(departmentName != null) return  adminService.viewDepartmentDateAttendance(departmentName, dayDate);
            if(jobName != null) return adminService.viewJobTitleDateAttendance(jobName, dayDate);
            return adminService.viewDateAttendance(dayDate);
        }
    }

    @PutMapping("/updateProfile/{user_id}")
    public Object updateProfile(@PathVariable int user_id, @RequestBody ProfileCreationDTO profileCreationDTO){
        return adminService.updateProfile(user_id, profileCreationDTO);
    }

    @PutMapping("/updateDepartment/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable int id, @RequestBody DepartmentRegistrationDTO departmentRegistrationDTO){
        if(adminService.updateDepartment(id, departmentRegistrationDTO)){
            return ResponseEntity.ok("Department name updated successfully");
        }
        return  ResponseEntity.badRequest().body("Department does not exist");
    }

    @PutMapping("/updateRole/{id}")
    public ResponseEntity<?> updateRole(@PathVariable int id, @RequestBody RoleRegistrationDTO roleRegistrationDTO){
        if(adminService.updateRole(id, roleRegistrationDTO)){
            return ResponseEntity.ok("Role name updated successfully");
        }
        return ResponseEntity.badRequest().body("Role does not exist");
    }

    @PutMapping("/updateJob/{id}")
    public Object updatejob(@PathVariable int id, @RequestBody JobTitleUpdateDTO jobTitleUpdateDTO){
        return adminService.updateJobTitle(id, jobTitleUpdateDTO);
    }

    @PutMapping("updateShift/{id}")
    public Object updateShift(@PathVariable int id, @RequestBody ShiftCreationDTO shiftCreationDTO){
        return adminService.updateShift(id, shiftCreationDTO);
    }
}
