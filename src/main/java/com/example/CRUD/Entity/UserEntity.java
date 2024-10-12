package com.example.CRUD.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uid;
    private String password;
    private String name;
    private String email;
    private String phone;
    private int age;
    private Boolean gender;
}
