package com.example.employeeleavemanagement.service;

import com.example.employeeleavemanagement.dao.UserRepository;
import com.example.employeeleavemanagement.model.UserPrincipal;
import com.example.employeeleavemanagement.model.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUsername(username).orElse(null);
         if(user == null)
         {
             System.out.println("User not found");
             log.error("User not found in database");
             throw new UsernameNotFoundException("User not found");
         }

         return new UserPrincipal(user);
    }
}
