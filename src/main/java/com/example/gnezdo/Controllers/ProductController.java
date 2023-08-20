package com.example.gnezdo.Controllers;

import com.example.gnezdo.Models.Products;
import com.example.gnezdo.Models.User;
import com.example.gnezdo.Repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductsRepository productsRepository;
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping()
    public String mainproducts(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Products> product = productsRepository.findAll();
        if (filter != null && !filter.isEmpty()) {
            product = productsRepository.findByTitle(filter);
        } else {
            product = productsRepository.findAll();
        }
        model.addAttribute("products", product);
        model.addAttribute("filter", filter);
        return "products";
    }

    @GetMapping("/add")
    public String addproducts() {
        return "productAdd";
    }

    @PostMapping("/add")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String title, @RequestParam String description, Map<String, Object> model,
            @RequestParam("file") MultipartFile file) throws IOException {
        Products product = new Products(title, description, user);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            product.setFilename(resultFilename);
        }
        productsRepository.save(product);
        Iterable<Products> products = productsRepository.findAll();
        model.put("products", products);
        return "productAdd";
    }

    @GetMapping("/user-products/{user}")
    public String userProducts(
            @AuthenticationPrincipal User currentUser, @PathVariable User user,
            Model model, @RequestParam(required = false) Products product) {
        Set<Products> products = user.getProducts();
        model.addAttribute("products", products);
        model.addAttribute("product", product);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        return "productEdit";
    }

    @PostMapping("/user-products/{user}")
    public String updateProduct(
            @AuthenticationPrincipal User currentUser, @PathVariable Long user,
            @RequestParam("id") Products product, @RequestParam("title") String title,
            @RequestParam("price") String price, @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file) throws IOException {
        if (product.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(title)) {
                product.setTitle(title);
            }
            if (!StringUtils.isEmpty(description)) {
                product.setDescription(description);
            }
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + resultFilename));
                product.setFilename(resultFilename);
            }
            productsRepository.save(product);
        }
        return "redirect: /products/user-products/" + user;
    }
}
