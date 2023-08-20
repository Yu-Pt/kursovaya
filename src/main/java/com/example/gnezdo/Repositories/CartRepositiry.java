package com.example.gnezdo.Repositories;

import com.example.gnezdo.Models.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepositiry extends CrudRepository<Cart, Long> {
    List<Cart> findByIduser(Long iduser);
}
