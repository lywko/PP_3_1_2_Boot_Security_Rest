package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {

    public List<User> index();

    public User show(long id);

    public void save(User user, String[] roles, String pass);
    public void save(User user);
    public void update(User user, String[] roles);

    public void update(User user);

    public void delete(long id);

    public User findByName(String name);
}
