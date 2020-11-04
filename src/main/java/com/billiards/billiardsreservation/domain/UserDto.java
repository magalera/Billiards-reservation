package com.billiards.billiardsreservation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    private String login;
    private String email;
    private String password;
    private Set<ReservationDto> reservationsDto;
}
