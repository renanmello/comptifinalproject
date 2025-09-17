package com.codifica.compti.models.profile;


import com.codifica.compti.models.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "profiles")
@Getter
@Setter
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String theme;
    private Boolean notificationsEnabled;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}