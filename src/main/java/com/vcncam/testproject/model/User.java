package com.vcncam.testproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "user_id")
    private Long userId;
    private String username;
    private String password;
    private boolean enabled;
    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable (name = "users_roles",
      joinColumns = @JoinColumn (name = "user_id"),
      inverseJoinColumns = @JoinColumn (name = "roles_id"))
    private Set<Role> roles = new HashSet<>();
}
