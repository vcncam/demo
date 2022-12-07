package com.vcncam.testproject.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table (name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "role_id")
    private Long roleId;
    private String name;
}
