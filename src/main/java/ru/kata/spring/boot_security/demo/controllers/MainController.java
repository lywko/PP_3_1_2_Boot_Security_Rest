package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.security.Principal;
import java.util.Arrays;
import java.util.stream.Collectors;


@Controller
public class MainController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/user")
    public String user(ModelMap model, Principal principal) {
        User user = userService.findByName(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin")
    public String index(Model model, Principal principal) {
        User user = userService.findByName(principal.getName());
        User userNew = new User();
        model.addAttribute("users", userService.index());
        model.addAttribute("user", user);
        model.addAttribute("userNew", userNew);
        return "admin/index";
    }

    @PostMapping("/admin/save")
    public String save(@ModelAttribute("userNew") User userNew,
                       @RequestParam(value = "rolesList") String[] roles,
                       @ModelAttribute("password") String password) {
        userService.save(userNew, roles, password);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @PutMapping("/admin/{id}/update")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "rolesList") String [] roles) {

        userService.update(user, roles);
        return "redirect:/admin";
    }


}
