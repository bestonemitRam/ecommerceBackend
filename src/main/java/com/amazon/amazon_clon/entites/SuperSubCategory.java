package com.amazon.amazon_clon.entites;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SuperSubCategory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperSubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SupperSubCategoryName")
    @NotBlank
    @Size(min = 4, message = "title must contain at least 2 characters")
    private String title;


    // Many-to-One relationship with SubCategory

    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    // Correctly mapped to 'superSubCategory' in the Product entity
    @OneToMany(mappedBy = "superSubCategory", cascade = CascadeType.ALL)
    private List<Product> products;



}
