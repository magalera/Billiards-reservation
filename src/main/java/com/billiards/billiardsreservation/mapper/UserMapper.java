package com.billiards.billiardsreservation.mapper;


import com.billiards.billiardsreservation.domain.User;
import com.billiards.billiardsreservation.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    @Autowired
    private ReservationMapper reservationMapper;

    public User mapToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getLogin(),
                userDto.getEmail(),
                userDto.getPassword(),
                reservationMapper.mapToReservations(userDto.getReservationsDto()));
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getLogin(),
                user.getEmail(),
                user.getPassword(),
                reservationMapper.mapToReservationsDto(user.getReservations()));
    }

    public Set<User> mapToUsers(Set<UserDto> usersDto) {
        return usersDto.stream()
                .map(userDto -> new User(
                        userDto.getId(),
                        userDto.getLogin(),
                        userDto.getEmail(),
                        userDto.getPassword(),
                        reservationMapper.mapToReservations(userDto.getReservationsDto())))
                .collect(Collectors.toSet());
    }

    public Set<UserDto> mapToUsersDto(Set<User> users) {
        return users.stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getLogin(),
                        user.getEmail(),
                        user.getPassword(),
                        reservationMapper.mapToReservationsDto(user.getReservations())))
                .collect(Collectors.toSet());
    }
}
