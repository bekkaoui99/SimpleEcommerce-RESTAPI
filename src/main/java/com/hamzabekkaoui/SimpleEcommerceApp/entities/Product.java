package com.hamzabekkaoui.SimpleEcommerceApp.entities;

import lombok.*;

import javax.persistence.*;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Product   {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(length = 20)
    private String name;
    private String productImageLink;
    private Double price;
    private double quantity;
    private boolean selected;
    private boolean available;

    @ManyToOne
    private Category category;

}
