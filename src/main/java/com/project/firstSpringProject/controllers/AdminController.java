package com.project.firstSpringProject.controllers;

import com.project.firstSpringProject.dtos.DepartmentRegistrationDTO;
import com.project.firstSpringProject.dtos.JobTitleRegistrationDTO;
import com.project.firstSpringProject.dtos.RoleRegistrationDTO;
import com.project.firstSpringProject.entities.Department;
import com.project.firstSpringProject.entities.Role;
import com.project.firstSpringProject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/createDepartment")
    public Department createDepartment(@RequestBody DepartmentRegistrationDTO departmentRegistrationDTO){
        return adminService.createDepartment(departmentRegistrationDTO);
    }

    @PostMapping("/createRole")
    public ResponseEntity<?> createRole(@RequestBody RoleRegistrationDTO roleRegistrationDTO){
        if(adminService.createRole(roleRegistrationDTO)){
            return ResponseEntity.ok("Role created successfully");
        }
        return ResponseEntity.badRequest().body("Role already exists");
    }

    @PostMapping("/createJob")
    public ResponseEntity<?> createJob(@RequestBody JobTitleRegistrationDTO jobTitleRegistrationDTO){
        if(adminService.createJob(jobTitleRegistrationDTO).equals(true)){
            return ResponseEntity.ok("Job Title created successfully");
        }
        return  ResponseEntity.badRequest().body(adminService.createJob(jobTitleRegistrationDTO));
    }

    @DeleteMapping("/deleteDepartment/{name}")
    public ResponseEntity<String> deleteDepartment(@PathVariable String name){
        if(adminService.deleteDepartment(name)){
            return ResponseEntity.ok("Department deleted successfully");
        }
        return ResponseEntity.badRequest().body("Department does not exist");
    }

    @DeleteMapping("/deleteRole/{name}")
    public ResponseEntity<String> deleteRole(@PathVariable String name){
        if(adminService.deleteRole(name)){
            return ResponseEntity.ok("Role deleted successfully");
        }
        return ResponseEntity.badRequest().body("Role does not exist");
    }

    @DeleteMapping("/deleteUser/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username){
        if(adminService.deleteUser(username)){
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


}
