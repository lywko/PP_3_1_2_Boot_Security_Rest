package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

@Repository
public interface RoleDAO {
    Role findByName(String name);

    void save(Role role);

}
