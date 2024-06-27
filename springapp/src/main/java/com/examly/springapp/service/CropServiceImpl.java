package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Crop;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.CropRepo;
import com.examly.springapp.repository.UserRepo;

@Service
public class CropServiceImpl implements CropService {

    @Autowired 
    public CropRepo cropRepo;

    @Autowired
    public UserRepo userRepo;

    @Override
    public Crop addCrop(Crop crop) {
        int userId=crop.getUser().getUserId();
        User user = userRepo.findById(userId).orElse(null);
        crop.setUser(user);
        return cropRepo.save(crop);
    }

   

    @Override
    public Crop updateCrop(int cropId,Crop crop) {
        Crop oldCrop = cropRepo.findById(cropId).orElse(null);
        crop.setCropId(cropId);
        return cropRepo.save(crop);
    }

    @Override
    public Crop deleteCrop(int cropId) {
        Crop deleteCrop = cropRepo.findById(cropId).orElse(null);
        cropRepo.deleteById(cropId);
        return deleteCrop;
    }



    @Override
    public List<Crop> getCropByUserId(int userId) {
        User user = userRepo.findById(userId).orElse(null);
        return cropRepo.findByUser(user);
    }
    
}
