package com.example.gnezdo.Repositories;


import com.example.gnezdo.Models.Products;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends CrudRepository<Products, Long> {
    List<Products> findByTitle(String title);
}
