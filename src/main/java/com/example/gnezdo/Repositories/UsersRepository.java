package com.example.gnezdo.Repositories;

import com.example.gnezdo.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}