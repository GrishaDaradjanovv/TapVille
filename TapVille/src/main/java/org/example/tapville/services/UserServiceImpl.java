package org.example.tapville.services;

import org.example.tapville.exceptions.EntityNotFoundException;
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
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public void create(User user) {
//        checkIfUsernameExist(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
