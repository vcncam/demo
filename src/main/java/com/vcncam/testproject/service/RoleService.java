package com.vcncam.testproject.service;

import com.vcncam.testproject.model.Role;
import com.vcncam.testproject.model.UserRole;
import com.vcncam.testproject.repository.RoleRepository;
import com.vcncam.testproject.repository.UserRepository;
import com.vcncam.testproject.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    
    public List<Role> getAllByUserId(Long userId) {
        return roleRepository.getAllByUserId(userId);
    }
    
    public List<Role> saveRoles(Long userId, Set<Role> roles) {
        List<Role> newRoles = roles.stream().filter(role -> !userRoleRepository.existsByUserIdAndRoleId(userId, role.getRoleId())).collect(Collectors.toList());
        List<UserRole> userRoles = newRoles.stream().map(role -> UserRole.builder()
            .userId(userId)
            .roleId(role.getRoleId())
            .build()).collect(Collectors.toList());
        userRoleRepository.saveAll(userRoles);
        
        return getAllByUserId(userId);
    }
}
