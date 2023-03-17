package com.rinoarias.holoapi.services.implementations;

import com.rinoarias.holoapi.entities.User;
import com.rinoarias.holoapi.models.dtos.user.*;
import com.rinoarias.holoapi.repositories.UserRepository;
import com.rinoarias.holoapi.services.interfaces.IUserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private UserRepository userRepository;
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getUsersByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    @Override
    public List<User> getUsersByBirthDateRange(LocalDate startDate, LocalDate endDate) {
        return userRepository.findByBirthDateBetween(startDate, endDate);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Otros métodos personalizados según los requisitos de tu aplicación
    @Override
    public UserResponseDTO registerNewUser(UserCreateDTO userDto) throws Exception {
        // Comprobar que el correo electrónico no está en uso
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new Exception("El correo electrónico ya está en uso");
        }

        // Crear un nuevo objeto User y asignar los valores de UserDto
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setBirthDate(userDto.getBirthDate());
        user.setGender(userDto.getGender());
        user.setDni(userDto.getDni());
        user.setEmail(userDto.getEmail());

        // Codificar la contraseña antes de guardarla
        user.setPassword(userDto.getPassword());

        // Guardar el nuevo usuario en la base de datos
        User savedUser = userRepository.save(user);

        // Devolver un objeto UserDto con los datos del nuevo usuario
        return UserResponseDTO.fromUser(savedUser);
    }



    @Override
    public UserResponseDTO loginUser(UserLoginDTO loginDto) throws Exception {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(loginDto.getEmail()));
        if (userOptional.isEmpty()) {
            throw new Exception("Usuario no encontrado");
        }
        User user = userOptional.get();
        if (loginDto.getPassword().compareTo(user.getPassword()) != 0) {
            throw new Exception("Contraseña incorrecta");
        }
        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthDate(user.getBirthDate())
                .gender(user.getGender())
                .dni(user.getDni())
                .email(user.getEmail())
                .build();
    }

}
