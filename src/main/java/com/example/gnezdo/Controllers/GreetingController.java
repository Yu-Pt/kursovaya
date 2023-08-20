package com.example.gnezdo.Controllers;

import com.example.gnezdo.Models.Products;
import com.example.gnezdo.Repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
    @Autowired
    private ProductsRepository productsRepository;
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Products> products = productsRepository.findAll();
        if (filter != null && !filter.isEmpty()) {
            products = productsRepository.findByTitle(filter);
        } else {
            products = productsRepository.findAll();
        }
        model.addAttribute("products", products);
        model.addAttribute("filter", filter);
        return "main";
    }
}
