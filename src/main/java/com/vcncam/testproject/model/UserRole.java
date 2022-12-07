package com.vcncam.testproject.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users_roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column (name = "roles_id")
    private Long roleId;
}
