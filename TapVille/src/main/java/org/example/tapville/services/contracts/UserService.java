package org.example.tapville.services.contracts;

import org.example.tapville.models.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getById(long id);

    void create(User user);

    void update(User user);

    User getByUsername(String username);

    User promoteToAdmin(User user, User executor);

    User demote(User user, User executor);
}
