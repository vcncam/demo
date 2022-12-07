package com.vcncam.testproject.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table (name = "product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "product_id")
    private Long productId;
    @Column (name = "product_name")
    private String productName;
    private String country;
    private float price;
}
