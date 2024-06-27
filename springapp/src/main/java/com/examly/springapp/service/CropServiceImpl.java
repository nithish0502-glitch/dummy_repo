package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Crop;
import com.examly.springapp.repository.CropRepo;

@Service
public class CropServiceImpl implements CropService {

    @Autowired 
    public CropRepo cropRepo;

    @Override
    public Crop addCrop(Crop crop) {
        return cropRepo.save(crop);
    }

    @Override
    public Crop getCropById(int cropId) {
        return cropRepo.findById(cropId).orElse(null);
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
    
}
