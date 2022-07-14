package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserDAO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> index() {
        return userDAO.findAll();
    }


    public User show(int id) {
        return userDAO.getById(id);
    }


    public void save(User user, String[] roles, String password) {

        user.setPassword(passwordEncoder.encode(password));

        user.setRoles(Arrays.stream(roles)
                .map(roleService::findByName)
                .collect(Collectors.toSet()));
        userDAO.save(user);
    }

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRoles(Arrays.stream(user.getRolesNames().split("!"))
                .map(roleService::findByName)
                .collect(Collectors.toSet()));
        userDAO.save(user);
    }

    public void update(User user, String[] roles) {
        User userToBeUpdated = userDAO.getById(user.getId());

        if (user.getPassword().equals("")) {
            user.setPassword(userToBeUpdated.getPassword());
        } else{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setRoles(Arrays.stream(roles)
                .map(roleService::findByName)
                .collect(Collectors.toSet()));
        userDAO.save(user);
    }

    @Override
    public void update(User user) {
        User userToBeUpdated = userDAO.getById(user.getId());

        if (user.getPassword().equals("")) {
            user.setPassword(userToBeUpdated.getPassword());
        } else{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setRoles(Arrays.stream(user.getRolesNames().split("!"))
                .map(roleService::findByName)
                .collect(Collectors.toSet()));
        userDAO.save(user);
    }


    public void delete(int id) {
        userDAO.deleteById(id);
    }

    public User findByName(String name) {
        return userDAO.findByName(name);

    }

}
