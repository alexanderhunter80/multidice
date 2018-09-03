package com.epoch.multidice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.epoch.multidice.models.Role;
import com.epoch.multidice.models.User;
import com.epoch.multidice.repositories.UserRepository;

@Service
public class UserDetailsSvc implements UserDetailsService {
    private UserRepository userRepository;
    
    public UserDetailsSvc(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	
        User user = userRepository.findByEmail(email).get();
        
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
    }
    
    private List<GrantedAuthority> getAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(Role role : user.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
            System.out.println("UserDetailsSvc queried database for user "+user.getEmail()+" and found authority "+role.getName());
            authorities.add(grantedAuthority);
        }
        return authorities;
    }
}