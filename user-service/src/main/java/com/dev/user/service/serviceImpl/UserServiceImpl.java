package com.dev.user.service.serviceImpl;

import com.dev.user.service.dto.UserDto;
import com.dev.user.service.exception.UserNotFoundException;
import com.dev.user.service.model.User;
import com.dev.user.service.repository.UserRepository;
import com.dev.user.service.service.UserService;
import com.dev.common_dto.mapper.DtoEntityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	

	@Autowired
    private UserRepository userRepository;
	
    private final DtoEntityMapper<UserDto, User> mapper;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.mapper = DtoEntityMapper.getDtoEntityMapper();
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = mapper.convertFromDtoToEntity(userDto, User.class);
        User saved = userRepository.save(user);
        return mapper.convertFromEntityToDto(saved, UserDto.class);
    }

    @Override
    public UserDto getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return mapper.convertFromEntityToDto(user, UserDto.class);
    }

    @Override
    public Optional<UserDto> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> mapper.convertFromEntityToDto(user, UserDto.class));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapper.convertFromEntityToDto(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(String id, UserDto userDto) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        User updatedUser = mapper.convertFromDtoToEntity(userDto, User.class);

        updatedUser.setId(id);

        User savedUser = userRepository.save(updatedUser);

        return mapper.convertFromEntityToDto(savedUser, UserDto.class);
    }


    @Override
    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
} 