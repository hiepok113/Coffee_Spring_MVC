package com.example.demo.service;



import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       com.example.demo.domain.User user = userService.getUserByEmail(email);
       if (user == null) {
           throw new UsernameNotFoundException("User not found");
       }
       if(user.getRole() == null) {
           throw new UsernameNotFoundException("User role not found");
       }
       return new CustomUserDetails(user);
    }
}
