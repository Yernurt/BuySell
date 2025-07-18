
package com.example.buysell.controllers;

import com.example.buysell.models.User;
import com.example.buysell.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    public String createUser(@RequestBody User user) {
        if (!userService.createUser(user)) {
            return "User with email " + user.getEmail() + " already exists";
        }
        return "User registered successfully";
    }
}

/*
package com.example.buysell.controllers;

import com.example.buysell.models.User;
import com.example.buysell.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";

    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@Valid User user,  BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            // Если есть ошибки, перенаправляем пользователя на страницу регистрации с сообщениями об ошибках
            model.addAttribute("user", user);
            return "registration";
        }

        if(!userService.createUser(user)){
            model.addAttribute("errorMessage", "Пользователь с email"+user.getEmail()+"уже существует");
            return "registration";

        }
        return "redirect:/login";
    }

}
*/
