package com.dev.user.service.service;

import java.util.List;
import java.util.Optional;

import com.dev.user.service.dto.UserDto;

public interface UserService {

    UserDto registerUser(UserDto userDto);

    UserDto getUserById(String id);

    Optional<UserDto> getUserByEmail(String email);

    List<UserDto> getAllUsers();

    UserDto updateUser(String id, UserDto userDto);

    void deleteUser(String id);
}
