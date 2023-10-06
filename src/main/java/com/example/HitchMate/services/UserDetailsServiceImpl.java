package com.example.HitchMate.services;

import com.example.HitchMate.Entity.User;
import com.example.HitchMate.repositories.UserRepository;
import com.example.HitchMate.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //method will be invoked by Spring Security when authenticating the users
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException
    {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Could not find user: " + username);
        }
        return new MyUserDetails(user);
    }
}
