package com.rinoarias.holoapi.services.interfaces;

import com.rinoarias.holoapi.entities.User;
import com.rinoarias.holoapi.models.dtos.user.UserLoginDTO;
import com.rinoarias.holoapi.models.dtos.user.UserCreateDTO;
import com.rinoarias.holoapi.models.dtos.user.UserResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface IUserService {
    User getUserByEmail(String email);
    List<User> getUsersByLastName(String lastName);
    List<User> getUsersByBirthDateRange(LocalDate startDate, LocalDate endDate);
    void saveUser(User user);
    void deleteUser(Long id);
    UserResponseDTO registerNewUser(UserCreateDTO userDto) throws Exception;
    UserResponseDTO loginUser(UserLoginDTO loginDto) throws Exception;
}
