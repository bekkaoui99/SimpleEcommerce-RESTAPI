package com.hamzabekkaoui.SimpleEcommerceApp.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String categoryName;

    private String categoryDescription;

    @OneToMany(mappedBy = "category")
    List<Product> products;
}
