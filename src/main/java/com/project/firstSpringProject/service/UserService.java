package com.project.firstSpringProject.service;

import com.project.firstSpringProject.dtos.UserRegistrationDTO;
import com.project.firstSpringProject.entities.Role;
import com.project.firstSpringProject.entities.User;
import com.project.firstSpringProject.repositories.RoleRepository;
import com.project.firstSpringProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);



    public void assignRole(User user, String roleName){
        Set<Role> role = new HashSet<>();
        role.add(roleRepository.findByNameLike(roleName));
        user.setRoles(role);
    }

    public String checkUsername(UserRegistrationDTO userRegistrationDTO){
        if(userRepository.findByUsername(userRegistrationDTO.getUsername()) != null){
            return userRegistrationDTO.getUsername();
        }
        return null;
    }

    public String checkEmail(UserRegistrationDTO userRegistrationDTO){
        if(userRepository.findByEmail(userRegistrationDTO.getEmail()) != null){
            return userRegistrationDTO.getEmail();
        }
        return null;
    }

    public List<String> validateUser(UserRegistrationDTO userRegistrationDTO){
        List<String> errors = new ArrayList<>();

        String username = userRegistrationDTO.getUsername();
        String firstName = userRegistrationDTO.getFirstName();
        String lastName = userRegistrationDTO.getLastName();
        String email = userRegistrationDTO.getEmail();
        String password = userRegistrationDTO.getPassword();
        String confirmPassword = userRegistrationDTO.getConfirmPassword();

        if((username.equals("")) || (firstName.equals("")) || (lastName.equals("")) || (email.equals("")) || (password.equals(""))){
            errors.add("Missing field/s");
        }

        if(checkUsername(userRegistrationDTO) != null){
            errors.add("User already exists");
        }

        if(checkEmail(userRegistrationDTO) != null){
            errors.add("Email already exists");
        }

        if(password.length() < 8){
            errors.add("Password length must be atleast 8 characters");
        }

        if(!password.equals(confirmPassword)){
            errors.add("Passwords do not match");
        }

        return errors;
    }

    public Object registerAdmin(UserRegistrationDTO userRegistrationDTO){
        var errors = validateUser(userRegistrationDTO);
        if(!errors.isEmpty()){
            System.out.println(validateUser(userRegistrationDTO));
            return errors;
        }
        var user = User.builder()
                .username(userRegistrationDTO.getUsername())
                .firstName(userRegistrationDTO.getFirstName())
                .lastName(userRegistrationDTO.getLastName())
                .email(userRegistrationDTO.getEmail())
                .password(encoder.encode(userRegistrationDTO.getPassword()))
                .build();

        assignRole(user, "ADMIN");
        userRepository.save(user);
        return true;
    }

    public Object registerStaff(UserRegistrationDTO userRegistrationDTO){
        var errors = validateUser(userRegistrationDTO);
        if(!errors.isEmpty()){
            System.out.println(validateUser(userRegistrationDTO));
            return errors;
        }
        var user = User.builder()
                .username(userRegistrationDTO.getUsername())
                .firstName(userRegistrationDTO.getFirstName())
                .lastName(userRegistrationDTO.getLastName())
                .email(userRegistrationDTO.getEmail())
                .password(encoder.encode(userRegistrationDTO.getPassword()))
                .build();

        assignRole(user, "STAFF");
        userRepository.save(user);
        return true;
    }

    public String verify(User user){
        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }

        return "Authentication Failed";
    }
}
