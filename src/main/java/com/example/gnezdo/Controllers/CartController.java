package com.example.gnezdo.Controllers;

import com.example.gnezdo.Models.Cart;
import com.example.gnezdo.Models.User;
import com.example.gnezdo.Repositories.CartRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartRepositiry cartRepositiry;

    @GetMapping("{user}")
    public String userCart(
            @AuthenticationPrincipal User currentUser, @PathVariable User user,
            Model model, @RequestParam(required = false) Cart cart) {
        Set<Cart> carts = user.getCarts();
        model.addAttribute("carts", carts);
        model.addAttribute("cart", cart);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        return "cart";
    }

    @PostMapping("/add")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String title, @RequestParam String price, Map<String, Object> model,
            @RequestParam("file") MultipartFile file) throws IOException {
        Cart cart = new Cart(user.getId(), title, price);
        cartRepositiry.save(cart);
        Iterable<Cart> carts = cartRepositiry.findAll();
        model.put("carts", carts);
        return "redirect: /events";
    }
}
