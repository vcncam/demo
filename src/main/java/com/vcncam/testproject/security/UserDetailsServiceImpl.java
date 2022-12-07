package com.vcncam.testproject.security;

import com.vcncam.testproject.model.Role;
import com.vcncam.testproject.model.User;
import com.vcncam.testproject.repository.RoleRepository;
import com.vcncam.testproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username Not Found: " + username ));
        List<Role> roles = roleRepository.getAllByUserId(user.getUserId());
        user.setRoles(new HashSet<>(roles));
        return new UserDetailsImpl(user);
    }
}
