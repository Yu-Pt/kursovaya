package com.example.gnezdo.Controllers;

import com.example.gnezdo.Models.Role;
import com.example.gnezdo.Models.User;
import com.example.gnezdo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user) {
        userService.saveUser(user, username, form);
        return "redirect:/user";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String username) {
        userService.updateProfile(user, password, username);
        return "redirect:/user/profile";
    }

    @GetMapping("/cart/{user}")
    public String getCart(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        return "cart";
    }

    @PostMapping("/cart/{user}")
    public String updateCart(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String username) {
        userService.updateProfile(user, password, username);
        return "redirect:/user/cart/{user}";
    }
}
