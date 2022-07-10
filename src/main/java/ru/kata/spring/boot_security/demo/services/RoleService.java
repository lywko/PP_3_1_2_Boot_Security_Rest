package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

public interface RoleService {

    public void save(Role role);

    Role findByName (String role);
}
