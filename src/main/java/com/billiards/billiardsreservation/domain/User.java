package com.billiards.billiardsreservation.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String login;
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private Set<Reservation> reservations;
}
