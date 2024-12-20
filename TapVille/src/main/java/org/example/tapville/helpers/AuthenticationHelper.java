package org.example.tapville.helpers;

import jakarta.servlet.http.HttpSession;
import org.example.tapville.exceptions.AuthorizationException;
import org.example.tapville.exceptions.EntityNotFoundException;
import org.example.tapville.models.User;
import org.example.tapville.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationHelper {
    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private static final String INVALID_AUTHENTICATION_ERROR = "Invalid username or password.";

    private final UserService userService;

    @Autowired
    public AuthenticationHelper(UserService userService) {
        this.userService = userService;
    }

    public User tryGetUser(HttpHeaders headers) {
        if (!headers.containsKey(AUTHORIZATION_HEADER_NAME)) {
            throw new AuthorizationException(INVALID_AUTHENTICATION_ERROR);
        }

        String userInfo = headers.getFirst(AUTHORIZATION_HEADER_NAME);
        String username = getUsername(userInfo);
        String password = getPassword(userInfo);
        return verifyAuthentication(username, password);
    }
    public User tryGetUser(String headers) {

        if (headers == null) {
            throw new AuthorizationException("Invalid authorization header");
        }

        String[] parts = headers.split(":");
        if (parts.length != 2) {
            throw new AuthorizationException("Invalid username/password format");
        }

        String username = parts[0];
        String password = parts[1];

        return verifyAuthentication(username, password);
    }
    public User tryGetCurrentUser(HttpSession session) {
        String currentUsername = (String) session.getAttribute("currentUser");

        if (currentUsername == null) {
            throw new AuthorizationException(INVALID_AUTHENTICATION_ERROR);
        }

        return userService.getByUsername(currentUsername);
    }

    public String getUsername(String userInfo) {
        int firstSpace = userInfo.indexOf(" ");
        if (firstSpace == -1) {
            throw new AuthorizationException(INVALID_AUTHENTICATION_ERROR);
        }

        return userInfo.substring(0, firstSpace);
    }

    public String getPassword(String userInfo) {
        int firstSpace = userInfo.indexOf(" ");
        if (firstSpace == -1) {
            throw new AuthorizationException(INVALID_AUTHENTICATION_ERROR);
        }

        return userInfo.substring(firstSpace + 1);
    }

    public User verifyAuthentication(String username, String password) {
        try {
            User user = userService.getByUsername(username);
            if (!user.getPassword().equals(password)) {
                throw new AuthorizationException(INVALID_AUTHENTICATION_ERROR);
            }
            return user;
        } catch (EntityNotFoundException e) {
            throw new AuthorizationException(INVALID_AUTHENTICATION_ERROR);
        }
    }
    public User getById(int id){
        return userService.getById(id);
    }
}
