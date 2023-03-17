package com.rinoarias.holoapi.models.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO implements Serializable {
    private String email;
    private String password;
}
