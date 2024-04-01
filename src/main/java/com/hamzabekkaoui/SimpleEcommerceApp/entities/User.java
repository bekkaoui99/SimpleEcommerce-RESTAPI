package com.hamzabekkaoui.SimpleEcommerceApp.entities;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;
    private String lastName;

    private String userName;
    private String mail;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
