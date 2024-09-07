package com.amazon.amazon_clon.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Category
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "categoryName")
   @NotBlank
   @Size(min = 4, message = "title must contain atleast 2 characters")
   private String title;

   @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<SubCategory> subcategories = new ArrayList<>();
   @CreatedDate
   @Column(name = "created_on", updatable = false)
   private LocalDateTime createdOn;

   @LastModifiedDate
   @Column(name = "updated_on")
   private LocalDateTime updatedOn;



}
