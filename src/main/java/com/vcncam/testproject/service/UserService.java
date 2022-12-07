package com.vcncam.testproject.service;

import com.vcncam.testproject.exception.UnauthorizedException;
import com.vcncam.testproject.exception.UserNotFoundException;
import com.vcncam.testproject.model.Role;
import com.vcncam.testproject.model.User;
import com.vcncam.testproject.repository.UserRepository;
import com.vcncam.testproject.util.Common;
import com.vcncam.testproject.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public List<Role> getRoles(Long userId) {
        return roleService.getAllByUserId(userId);
    }
    public User getUserInformation(String username) {
        User user = userRepository.getUserByUsername(username).orElseThrow(UserNotFoundException::new);
        List<Role> roles = getRoles(user.getUserId());
        user.setRoles(new HashSet<>(roles));
        return user;
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User editUserInformation(User user) {
        User currentUser = getCurrentUser();
        boolean isAdmin = currentUser.getRoles().stream().anyMatch(role -> role.getName().equals(Constant.Roles.ADMIN));
        if (isAdmin) {
            List<Role> roles = roleService.saveRoles(user.getUserId(), user.getRoles());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User response = userRepository.save(user);
            response.setRoles(new HashSet<>(roles));
            return response;
        } else {
            if (!currentUser.getUserId().equals(user.getUserId())) {
                throw new UnauthorizedException();
            } else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRoles(new HashSet<>());
                return userRepository.save(user);
            }
        }
    }
    
    private User getCurrentUser() {
        String currentUsername = Common.getCurrentUser();
        return getUserInformation(currentUsername);
    }
}
