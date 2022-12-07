package com.vcncam.testproject.repository;

import com.vcncam.testproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query (value = "select r.role_id, r.name\n" +
        "from roles r join users_roles ur on r.role_id = ur.roles_id\n" +
        "where user_id = ?1", nativeQuery = true)
    List<Role> getAllByUserId (Long userId);
}
