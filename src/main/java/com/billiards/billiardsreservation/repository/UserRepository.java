package com.billiards.billiardsreservation.repository;

import com.billiards.billiardsreservation.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    Set<User> findAll();

    Optional<User> findByLoginAndPassword(String login, String password);

    @Override
    User save(User user);

    @Override
    Optional<User> findById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    void deleteAll();
}
