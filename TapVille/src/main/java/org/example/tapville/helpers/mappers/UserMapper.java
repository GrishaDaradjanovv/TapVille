package org.example.tapville.helpers.mappers;

import org.example.tapville.models.User;
import org.example.tapville.models.dtos.UserDto;
import org.example.tapville.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final UserService userService;

    @Autowired
    public UserMapper(UserService userService) {
        this.userService = userService;
    }

    public User dtoUserCreate(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public User updateUser(long id, UserDto userDto) {
        User user = userService.getById(id);
        user.setUsername(userDto.getUsername());
        return user;
    }

    public UserDto userToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    public User updatePassword(long id, UserDto userDto) {
        User user = userService.getById(id);
        user.setPassword(userDto.getPassword());
        return user;
    }
}
