package com.rinoarias.holoapi.repositories;

import com.rinoarias.holoapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByLastName(String lastName);
    List<User> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);

    boolean existsByEmail(String email);
}
