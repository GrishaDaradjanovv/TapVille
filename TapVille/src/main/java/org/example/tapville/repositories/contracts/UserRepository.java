package org.example.tapville.repositories.contracts;

import org.example.tapville.models.DiscountCard;
import org.example.tapville.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u")
    List<User> getAll();

    User findUserById(long id);

    User findUserByUsername(String username);
}
