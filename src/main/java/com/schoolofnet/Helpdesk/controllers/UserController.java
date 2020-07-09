package com.schoolofnet.Helpdesk.controllers;

import com.schoolofnet.Helpdesk.models.User;
import com.schoolofnet.Helpdesk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("list", this.userService.findAll());
        return "users/index";
    }


    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "users/create";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        User user = this.userService.show(id);
        model.addAttribute("user", user);
        return "users/edit";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "users/create";
        }
        this.userService.create(user);
        return "redirect:/users";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "user/edit";
        }
        this.userService.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        this.userService.delete(id);
        return "redirect:/users";
    }
}
