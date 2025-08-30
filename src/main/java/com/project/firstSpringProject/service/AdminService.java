package com.project.firstSpringProject.service;

import com.project.firstSpringProject.dtos.*;
import com.project.firstSpringProject.entities.Department;
import com.project.firstSpringProject.entities.Role;
import com.project.firstSpringProject.entities.User;
import com.project.firstSpringProject.repositories.DepartmentRepository;
import com.project.firstSpringProject.repositories.RoleRepository;
import com.project.firstSpringProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public Department createDepartment(DepartmentRegistrationDTO departmentRegistrationDTO){
        Department department = new Department();
        department.setName(departmentRegistrationDTO.getName());
        return departmentRepository.save(department);
    }

    public boolean deleteDepartment(String name){
        if(departmentRepository.findByName(name).isPresent()){
            departmentRepository.deleteByName(name);
            return true;
        }
        return false;
    }

    public boolean createRole(RoleRegistrationDTO roleRegistrationDTO){
        if(!roleRepository.findByName(roleRegistrationDTO.getName()).isPresent()){
            Role role = new Role();
            role.setName(roleRegistrationDTO.getName());
            roleRepository.save(role);
            return true;
        }
        return false;
    }

    public boolean deleteRole(String name){
        if(roleRepository.findByName(name).isPresent()){
            roleRepository.deleteByName(name);
            return true;
        }
        return false;
    }

    public boolean deleteUser(String username){
        if(userRepository.findByUsername(username) != null){
            userRepository.deleteByUsername(username);
            return true;
        }
        return false;
    }

    public List<UserSummary> getUsers(){
        return userRepository.findUsers();
    }

    public List<DepartmentSummary> getDepartments(){
        return departmentRepository.findDepartments();
    }

    public List<RoleSummary> getRoles(){
        return roleRepository.findRoles();
    }
}
