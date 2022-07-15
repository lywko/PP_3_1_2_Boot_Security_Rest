package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Repository
public interface UserDAO {
    User findByName(String name);
    List<User> findAll();
    User getById(long id);
    void save(User user);

    void deleteById(long id);

    void update(User user);

}
