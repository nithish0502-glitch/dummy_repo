package com.examly.springapp.service;

import com.examly.springapp.model.Gym;
import java.util.List;

public interface GymService {
    Gym addGym(Gym gym);
    Gym getGymById(Long id);
    List<Gym> getAllGyms();
}
