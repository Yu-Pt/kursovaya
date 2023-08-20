package com.example.gnezdo.Repositories;

import com.example.gnezdo.Models.Events;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventsRepository extends CrudRepository<Events, Long> {
    List<Events> findByTitle(String title);
}
