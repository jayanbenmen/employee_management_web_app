package com.project.firstSpringProject.service;

import com.project.firstSpringProject.dtos.*;
import com.project.firstSpringProject.entities.*;
import com.project.firstSpringProject.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private JobTitleRepository jobTitleRepository;

    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private ProfileRepository profileRepository;

    public boolean createDepartment(DepartmentRegistrationDTO departmentRegistrationDTO){
        if(departmentRepository.findByNameLike(departmentRegistrationDTO.getName()) == null){
            Department department = new Department();
            department.setName(departmentRegistrationDTO.getName());
            departmentRepository.save(department);
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

    public Object createJob(int id, JobTitleRegistrationDTO jobTitleRegistrationDTO){
        List<String> errors = new ArrayList<>();
        if(!jobTitleRepository.findByNameLike(jobTitleRegistrationDTO.getName()).isPresent()){
            Department department = departmentRepository.findById(id);
            if(department != null){
                JobTitle jobTitle = new JobTitle();
                jobTitle.setName(jobTitleRegistrationDTO.getName());
                jobTitle.setDepartment(department);
                jobTitleRepository.save(jobTitle);
                return true;
            }
            else{
                errors.add("Department does not exist");
                return errors;
            }
        }
        errors.add("Job Title already exists");
        return errors;
    }

    public Object createShift(ShiftCreationDTO shiftCreationDTO){
        Shift shift = shiftRepository.findByNameLike(shiftCreationDTO.getName());
        List<String> errors = new ArrayList<>();
        if(shift != null){
            errors.add("Shift already exists");
            return errors;
        }
        Shift newShift = new Shift();
        newShift.setName(shiftCreationDTO.getName());
        newShift.setStartTime(shiftCreationDTO.getStartTime());
        newShift.setEndTime(shiftCreationDTO.getEndTime());
        newShift.setMultiplier(shiftCreationDTO.getMultiplier());
        shiftRepository.save(newShift);

        ShiftSummary shiftSummary = new ShiftSummary();
        shiftSummary.setId(newShift.getId());
        shiftSummary.setName(newShift.getName());
        shiftSummary.setStartTime(newShift.getStartTime());
        shiftSummary.setEndTime(newShift.getEndTime());
        shiftSummary.setMultiplier(newShift.getMultiplier());

        return shiftSummary;

//        return new ShiftSummary(
//                newShift.getId(),
//                newShift.getName(),
//                newShift.getStartTime(),
//                newShift.getEndTime(),
//                newShift.getMultiplier()
//        );
    }

    public Object createProfile(int id, ProfileCreationDTO profileCreationDTO){
        User user = userRepository.findById(id);
        JobTitle jobTitle = jobTitleRepository.findByName(profileCreationDTO.getJobName());
        Shift shift = shiftRepository.findByNameLike(profileCreationDTO.getShiftName());
        List<String> errors = new ArrayList<>();

        if(user == null){
            errors.add("User does not exist");
            return errors;
        }

        if(jobTitle == null){
            errors.add("Job title does not exist");
            return errors;
        }

        if(shift == null){
            errors.add("Shift does not exist");
            return errors;
        }

        Profile profile = new Profile();
        profile.setDuration(profileCreationDTO.getDuration());
        profile.setHourlyRate(profileCreationDTO.getHourlyRate());
        profile.setNfc(profileCreationDTO.getNfc());
        profile.setUser(user);
        profile.setJobTitle(jobTitle);
        profile.setShift(shift);
        profileRepository.save(profile);
        return profileRepository.findProfileById(profile.getId());
    }

    public Object createProfile(){
        return true;
    }

    public boolean deleteDepartment(int id){
        if(departmentRepository.findById(id) != null){
            departmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteRole(int id){
        if(roleRepository.findById(id) != null){
            roleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteJobTitle(int id){
        if(jobTitleRepository.findById(id) != null){
            jobTitleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteUser(int id){
        if(userRepository.findById(id) != null){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<DepartmentSummary> getDepartments(){
        return departmentRepository.findDepartments();
    }

    public List<RoleSummary> getRoles(){
        return roleRepository.findRoles();
    }

    public List<JobTitleSummary> getJobs(){
        return jobTitleRepository.findJobs();
    }

    public List<UserSummary> getUsers(){
        return userRepository.findUsers();
    }

    public List<ShiftSummary> getShifts(){
        return shiftRepository.findShifts();
    }

    public ProfileSummary searchProfile(String username){
        return profileRepository.findProfileByUsername(username);
    }

    public DepartmentSummary searchDepartment(String name){
        return departmentRepository.findByName(name);
    }

    public UserSummary searchUser(String username){
        return userRepository.findByUsernameLike(username);
    }

    public Role searchRole(String name){
        return roleRepository.findByNameLike(name);
    }

    public JobTitleSummary searchJobTitle(String name){
        return jobTitleRepository.findJobTitleByName(name);
    }

    public boolean updateDepartment(int id, DepartmentRegistrationDTO departmentRegistrationDTO){
        Department department = departmentRepository.findById(id);
        if(department != null){
            department.setName(departmentRegistrationDTO.getName());
            departmentRepository.save(department);
            return true;
        }
        return false;
    }

    public boolean updateRole(int id, RoleRegistrationDTO roleRegistrationDTO){
        Role role = roleRepository.findById(id);
        if(role != null){
            role.setName(roleRegistrationDTO.getName());
            roleRepository.save(role);
            return true;
        }
        return false;
    }

    public Object updateJobTitle(int id, JobTitleUpdateDTO jobTitleUpdateDTO){
        JobTitle jobTitle = jobTitleRepository.findById(id);
        List<String> errors = new ArrayList<>();
        if(jobTitle != null){
            jobTitle.setName(jobTitleUpdateDTO.getName());
            Department department = departmentRepository.findByNameLike(jobTitleUpdateDTO.getDepartmentName());
            if(department != null){
                jobTitle.setDepartment(department);
                jobTitleRepository.save(jobTitle);
                return jobTitleRepository.findJobTitleById(id);

            }
            errors.add("Department does not exist");
            return errors;
        }
        errors.add("Job Title does not exist");
        return errors;
    }

    public Object updateShift(int id, ShiftCreationDTO shiftCreationDTO){
        Shift shift = shiftRepository.findShiftById(id);
        List<String> errors = new ArrayList<>();
        if(shift == null){
            errors.add("Shift does not exist");
            return errors;
        }
        shift.setName(shiftCreationDTO.getName());
        shift.setStartTime(shiftCreationDTO.getStartTime());
        shift.setEndTime(shiftCreationDTO.getEndTime());
        shift.setMultiplier(shiftCreationDTO.getMultiplier());
        shiftRepository.save(shift);

        ShiftSummary shiftSummary = new ShiftSummary();
        shiftSummary.setId(shift.getId());
        shiftSummary.setName(shift.getName());
        shiftSummary.setStartTime(shift.getStartTime());
        shiftSummary.setEndTime(shift.getEndTime());
        shiftSummary.setMultiplier(shift.getMultiplier());

        return shiftSummary;
    }

    public Object updateProfile(int user_id, ProfileCreationDTO profileCreationDTO){
        Profile profile = profileRepository.findProfileByUserId(user_id);
        JobTitle jobTitle = jobTitleRepository.findByName(profileCreationDTO.getJobName());
        Shift shift = shiftRepository.findByNameLike(profileCreationDTO.getShiftName());

        List<String> errors = new ArrayList<>();

        if(profile == null){
            errors.add("Profile does not exist");
            return errors;
        }

        if(jobTitle == null){
            errors.add("Job title does not exist");
            return errors;
        }

        if(shift == null){
            errors.add("Shift does not exist");
            return errors;
        }

        profile.setDuration(profileCreationDTO.getDuration());
        profile.setHourlyRate(profileCreationDTO.getHourlyRate());
        profile.setNfc(profileCreationDTO.getNfc());
        profile.setJobTitle(jobTitle);
        profile.setShift(shift);
        profileRepository.save(profile);

        return new ProfileSummary(
                profile.getId(),
                profile.getDuration(),
                profile.getHourlyRate(),
                profile.getNfc(),
                profile.getUser().getUsername(),
                profile.getJobTitle().getName(),
                profile.getShift().getName()
        );
    }


}
