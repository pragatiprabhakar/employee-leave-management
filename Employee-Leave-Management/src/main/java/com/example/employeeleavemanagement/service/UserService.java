package com.example.employeeleavemanagement.service;

import com.example.employeeleavemanagement.Exception.EmployeeNotFoundException;
import com.example.employeeleavemanagement.Exception.UserNameAlreadyExistException;
import com.example.employeeleavemanagement.dao.UserRepository;
import com.example.employeeleavemanagement.model.Role;
import com.example.employeeleavemanagement.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepository userRepo;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user)
    {
        if(userRepo.findByUsername(user.getUsername()).isPresent())
        {
            log.error("Username Already Exist");
            throw new UserNameAlreadyExistException("Username Already Exist");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        if(user.getRole() == null)
        {
            user.setRole(Role.EMPLOYEE);
        }
       return userRepo.save(user);
    }

    public List<Users> getAllUser() {
        return userRepo.findAll();
    }

    public Users removeUser(Long id)
    {
        Users user = userRepo.findById(id).orElseThrow(() -> {
            log.error("User not found by id {}" , id);
            return new EmployeeNotFoundException("User not found by id " + id);
        });
        userRepo.delete(user);
        return user;
    }



}
