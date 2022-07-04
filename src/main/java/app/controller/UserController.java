package app.controller;

import app.Model.User;
import app.Services.UserService;
import app.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String getUsers(Model model,
                           @ModelAttribute("new_user") User new_user) {
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

//    @PostMapping()
//    public String removeUser(@ModelAttribute("del_user") User user_del) {
//        userService.remove(user_del);
//        return "redirect:/";
//    }


}
