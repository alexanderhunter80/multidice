package com.epoch.multidice.services;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.epoch.multidice.models.User;
import com.epoch.multidice.repositories.RoleRepository;
import com.epoch.multidice.repositories.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder)     {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    
    public void saveUserWithUserRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }
      
    public void saveUserWithAdminRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
        userRepository.save(user);
    }    
    
    public void saveUserWithSuperRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_SUPER"));
        userRepository.save(user);
    }
    
    public boolean isAdmin(User user) {
    	return user.getRoles().contains(roleRepository.findByName("ROLE_ADMIN"));
    }
    
    public boolean isSuper(User user) {
    	return user.getRoles().contains(roleRepository.findByName("ROLE_SUPER"));
    }
    
    public User findByUsername(String username) {
        Optional<User> opt = userRepository.findByUsername(username);
        if(opt.isPresent()) {
        	return opt.get();
        } else {
        	return null;
        }
    }
    
    public User findById(Long id) {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()) {
        	return opt.get();
        } else {
        	return null;
        }
    }
    
    public User findByEmail(String email) {
        Optional<User> opt = userRepository.findByEmail(email);
        if(opt.isPresent()) {
        	return opt.get();
        } else {
        	return null;
        }
    }
    
}