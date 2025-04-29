package com.examly.springapp.controller;
import com.examly.springapp.model.Gym;
import com.examly.springapp.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gym")
public class GymController {

    @Autowired
    private GymService gymService;
    @PostMapping
    public ResponseEntity<?> addGym(@RequestBody Gym gym) {
        List<Gym> gyms = gymService.getAllGyms();
        boolean exists = gyms.stream().anyMatch(g ->
            g.getName().equalsIgnoreCase(gym.getName()) &&
            g.getLocation().equalsIgnoreCase(gym.getLocation())
        );
        if (exists) {
            return ResponseEntity.status(409).body("A gym with the same name and location already exists.");
        }
        Gym savedGym = gymService.addGym(gym);
        return ResponseEntity.status(201).body(savedGym);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGymById(@PathVariable Long id) {
        Gym gym = gymService.getGymById(id);
        if (gym == null) {
            return ResponseEntity.status(404).body("Gym with ID " + id + " not found.");
        }
        return ResponseEntity.ok(gym);
    }

    @GetMapping
    public ResponseEntity<?> getAllGyms() {
        List<Gym> gyms = gymService.getAllGyms();
        if (gyms.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(gyms);
    }
}
