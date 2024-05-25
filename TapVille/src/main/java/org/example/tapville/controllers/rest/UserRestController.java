package org.example.tapville.controllers.rest;

import jakarta.validation.Valid;
import org.example.tapville.exceptions.AuthorizationException;
import org.example.tapville.exceptions.InvalidOperationException;
import org.example.tapville.exceptions.UsernameDuplicateException;
import org.example.tapville.helpers.AuthenticationHelper;
import org.example.tapville.mappers.UserMapper;
import org.example.tapville.models.User;
import org.example.tapville.models.dtos.UserDto;
import org.example.tapville.services.contracts.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthenticationHelper authenticationHelper;

    public UserRestController(UserService userService, UserMapper userMapper, AuthenticationHelper authenticationHelper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.authenticationHelper = authenticationHelper;
    }

    @GetMapping
    public List<User> getAll(@RequestHeader(name = "Authorization") String header) {
        try{
            User user = authenticationHelper.tryGetUser(header);
            return userService.getAll(user);
        }catch (AuthorizationException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable long id) {
        return userService.getById(id);
    }

    @PostMapping
    public void create(@Valid @RequestBody UserDto userDto) {
        try {
            User user = userMapper.dtoUserCreate(userDto);
            userService.create(user);
        } catch (UsernameDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @Valid @RequestBody UserDto userDto) {
        try {
            User user = userMapper.updateUser(id, userDto);
            userService.update(user);
        } catch (UsernameDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("/{userId}/promote")
    public void promoteToAdmin(@PathVariable long userId) {
        try {
            User user = userService.getById(8);
            User userToPromote = userService.getById(userId);

            userService.promoteToAdmin(userToPromote, user);
        } catch (InvalidOperationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PutMapping("/{userId}/demote")
    public void demote(@PathVariable long userId) {
        try {
            User user = userService.getById(8);
            User userToPromote = userService.getById(userId);

            userService.demote(userToPromote, user);
        } catch (InvalidOperationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
