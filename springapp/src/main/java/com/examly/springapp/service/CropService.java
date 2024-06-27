package com.examly.springapp.service;

import com.examly.springapp.model.Crop;

public interface CropService {
    public Crop addCrop(Crop crop);
    public Crop getCropById(int cropId);
    public Crop updateCrop(int cropId,Crop crop);
    public Crop deleteCrop(int cropId);
}
