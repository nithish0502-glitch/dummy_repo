package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.examly.springapp.model.Shirt;
import com.examly.springapp.repository.ShirtRepo;

@Service
public class ApiService {

    @Autowired
    public ShirtRepo shirtRepo;

    public boolean addNewShirt(Shirt shirt) {
        return shirtRepo.save(shirt) != null ? true : false;
    }

    public Shirt getOneShirt(int shirtId) {
        return shirtRepo.findById(shirtId).get();
    }

    public List<Shirt> getAllShirt() {
        return shirtRepo.findAll();
    }

    public Shirt updateShirt(Shirt shirt, int shirtId) {
        return shirtRepo.save(shirt);
    }

    public boolean deleteOneShirt(int shirtId) {
        shirtRepo.deleteById(shirtId);
        return true;
    }

}
