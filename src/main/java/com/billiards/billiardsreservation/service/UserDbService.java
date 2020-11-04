package com.billiards.billiardsreservation.service;

import com.billiards.billiardsreservation.domain.User;
import com.billiards.billiardsreservation.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserDbService {

    private UserRepository userRepository;

    public Set<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public boolean validateUser(User loggingUser) {
        Set<User> all = userRepository.findAll();
        int counter = 0;
        for (User user : all) {
            if (loggingUser.getLogin().equals(user.getLogin())
                    && (loggingUser.getPassword().equals(user.getPassword()))) {
                counter++;
            }
        }
        return counter == 1;
    }

    /*public boolean findByLoginAndPassword(String login, String password) {
        Set<User> all = userRepository.findAll();
        int counter = 0;
        for (User user : all) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                counter++;
            }
        }
        return counter == 1;
    }*/

}

