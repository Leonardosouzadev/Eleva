package com.example.eleva.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "eleva", name = "profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String profileId;

    @Column(unique = true)
    private String profileName;

    @Column
    private String profileBiography;

    @Column
    private String profileImage;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Profile(String s, String s1, String s2) {
    }
}
