package com.billiards.billiardsreservation.controller;

import com.billiards.billiardsreservation.domain.User;
import com.billiards.billiardsreservation.domain.UserDto;
import com.billiards.billiardsreservation.mapper.UserMapper;
import com.billiards.billiardsreservation.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("v1/user")
public class UserController {
    @Autowired
    private UserDbService service;
    private UserMapper mapper;

    @PostMapping
    public void register(@RequestBody UserDto userDto) {
        service.save(mapper.mapToUser(userDto));
    }

    /*@GetMapping(value = "/{login}/{password}")
    public void logIn(@PathVariable String login, @PathVariable String password) {
        service.logIn(mapper.mapToUser(userDto));
    }*/

    @GetMapping(value = "/{id}")
    public UserDto get(@PathVariable Long id) throws UserNotFoundException {
        return mapper.mapToUserDto(service.findById(id).orElseThrow(UserNotFoundException::new));
    }

    @GetMapping
    public Set<UserDto> getAllUsers() {
        return mapper.mapToUsersDto(service.getAllUsers());
    }

    @PutMapping
    public UserDto edit(@RequestBody UserDto userDto){
        return mapper.mapToUserDto(service.save(mapper.mapToUser(userDto)));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) throws UserNotFoundException{
        User user = service.findById(id).orElseThrow(UserNotFoundException::new);
        service.delete(user.getId());
    }
}
