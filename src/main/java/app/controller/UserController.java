package app.controller;

import app.Model.User;
import app.Services.UserService;
import app.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping(value = "/")
    public String getUsers(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        return "users";
    }

    @GetMapping(value = "/create-user")
    public String addUserForm(@ModelAttribute User user) {
        return "create-user";
    }

    @PostMapping(value = "/create-user")
    public String addUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/";
    }



}
