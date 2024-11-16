package com.csOneCup.csOneCup.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
public class User {
    @Id
    @Column(length = 30, nullable = false, unique = true)
    private String userId;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String password;
}
