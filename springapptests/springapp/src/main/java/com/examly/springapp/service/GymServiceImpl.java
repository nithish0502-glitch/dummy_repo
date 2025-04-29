package com.examly.springapp.service;

import com.examly.springapp.model.Gym;
import com.examly.springapp.repository.GymRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymServiceImpl implements GymService {

    @Autowired
    private GymRepo gymRepo;

    @Override
    public Gym addGym(Gym gym) {
        return gymRepo.save(gym);
    }

    @Override
    public Gym getGymById(Long id) {
        return gymRepo.findById(id).orElse(null);
    }

    @Override
    public List<Gym> getAllGyms() {
        return gymRepo.findAll();
    }
}
