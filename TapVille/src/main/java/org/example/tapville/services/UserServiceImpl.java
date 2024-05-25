package org.example.tapville.services;

import org.example.tapville.exceptions.AuthorizationException;
import org.example.tapville.exceptions.EntityNotFoundException;
import org.example.tapville.exceptions.InvalidOperationException;
import org.example.tapville.exceptions.UsernameDuplicateException;
import org.example.tapville.models.User;
import org.example.tapville.repositories.contracts.UserRepository;
import org.example.tapville.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public List<User> getAll(User executor) {
        if (executor.isSuperAdmin()){
            return userRepository.getAll();
        }
        throw new AuthorizationException("User is not an admin and cannot perform this operation!");

    }

    @Override
    public void create(User user) {
//        checkIfUsernameExist(user);
        user.setPassword(user.getPassword());
        user.setCreationDate(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public User getById(long id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new EntityNotFoundException("User", "ID", String.valueOf(id));
        }
        return user;
    }

    @Override
    public User getByUsername(String username) {
        if (username == null) {
            throw new EntityNotFoundException("User", "username", username);
        }
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User promoteToAdmin(User user, User executor) {
        checkIfUserIsSuperAdmin(executor);
        User promotedUser = userRepository.findUserById(user.getId());
        promotedUser.setAdmin(true);
        update(promotedUser);
        return promotedUser;
    }

    @Override
    public User demote(User user, User executor) {
        checkIfUserIsSuperAdmin(executor);
        User demotedUser = userRepository.findUserById(user.getId());
        demotedUser.setAdmin(false);
        update(demotedUser);
        return demotedUser;
    }

    private void checkIfUsernameExist(User user) {
        boolean duplicateExists = true;
        try {
            User existingUsername = userRepository.findUserByUsername(user.getUsername());
            if (existingUsername.getId() == user.getId()) {
                duplicateExists = false;
            }
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new UsernameDuplicateException("User", "username", user.getUsername());
        }
    }
//    //todo for auth helper class
//    public boolean checkPassword(String rawPassword, String encodedPassword) {
//        return passwordEncoder.matches(rawPassword, encodedPassword);
//    }
    private void checkIfUserIsSuperAdmin(User user) {
        if (!user.isSuperAdmin()) {
            throw new InvalidOperationException("User is not an admin and cannot perform this operation!");
        }
    }
}
