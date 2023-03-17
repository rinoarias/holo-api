package com.rinoarias.holoapi.models.dtos.user;

import com.rinoarias.holoapi.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDTO implements Serializable {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String dni;
    private String email;
    private String password;
    public UserCreateDTO fromUser(User user) {
        return UserCreateDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthDate(user.getBirthDate())
                .gender(user.getGender())
                .dni(user.getDni())
                .email(user.getEmail())
                .build();
    }
}
