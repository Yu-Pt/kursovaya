package com.example.gnezdo.Controllers;

import com.example.gnezdo.Models.Events;
import com.example.gnezdo.Models.User;
import com.example.gnezdo.Repositories.EventsRepository;
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
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventsRepository eventsRepository;
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping()
    public String mainevents(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Events> event = eventsRepository.findAll();
        if (filter != null && !filter.isEmpty()) {
            event = eventsRepository.findByTitle(filter);
        } else {
            event = eventsRepository.findAll();
        }
        model.addAttribute("events", event);
        model.addAttribute("filter", filter);
        return "events";
    }

    @GetMapping("/add")
    public String addevents() {
        return "eventAdd";
    }

    @PostMapping("/add")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String title, @RequestParam String price,
            @RequestParam String description, @RequestParam String date, @RequestParam String place, Map<String, Object> model,
            @RequestParam("file") MultipartFile file) throws IOException {
        Events event = new Events(title, price, description, date, place, user);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            event.setFilename(resultFilename);
        }
        eventsRepository.save(event);
        Iterable<Events> events = eventsRepository.findAll();
        model.put("events", events);
        return "eventsAdd";
    }

    @GetMapping("/user-events/{user}")
    public String userEvents(
            @AuthenticationPrincipal User currentUser, @PathVariable User user,
            Model model, @RequestParam(required = false) Events event) {
        Set<Events> events = user.getEvents();
        model.addAttribute("events", events);
        model.addAttribute("event", event);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        return "eventEdit";
    }

    @PostMapping("/user-events/{user}")
    public String updateEvent(
            @AuthenticationPrincipal User currentUser, @PathVariable Long user,
            @RequestParam("id") Events event, @RequestParam("title") String title,
            @RequestParam("place") String place, @RequestParam("date") String date,
            @RequestParam("price") String price, @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file) throws IOException {
        if (event.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(title)) {
                event.setTitle(title);
            }
            if (!StringUtils.isEmpty(price)) {
                event.setPrice(price);
            }
            if (!StringUtils.isEmpty(description)) {
                event.setDescription(description);
            }
            if (!StringUtils.isEmpty(place)) {
                event.setDescription(place);
            }
            if (!StringUtils.isEmpty(date)) {
                event.setDescription(date);
            }
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + resultFilename));
                event.setFilename(resultFilename);
            }
            eventsRepository.save(event);
        }
        return "redirect: /events/user-events/" + user;
    }
}
