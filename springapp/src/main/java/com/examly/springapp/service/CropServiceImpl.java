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
        return cropRepo.findById(cropId);
    }

    @Override
    public Crop updateCrop(Crop crop) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCrop'");
    }

    @Override
    public Crop deleteCrop(int cropId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCrop'");
    }
    
}
