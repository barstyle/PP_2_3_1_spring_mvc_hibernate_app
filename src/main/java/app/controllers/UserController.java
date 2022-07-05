package app.controllers;

import app.models.User;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String getUsers(Model model,
                           @ModelAttribute("new_user") User new_user){
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        return "/users";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("new_user") @Valid User new_user,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "users";
        userService.save(new_user);
        return "redirect:/";
    }

    @GetMapping("/user-delete/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userService.remove(userService.getUserById(id));
        return "redirect:/";
    }

    @GetMapping("user-update/{id}")
    public String editUser(@PathVariable("id") Long id, Model model, Model model2) {
        User user = userService.getUserById(id);
        model.addAttribute("new_user", user);
        model2.addAttribute("users", userService.getAllUsers());
        return "/users";
    }
}
