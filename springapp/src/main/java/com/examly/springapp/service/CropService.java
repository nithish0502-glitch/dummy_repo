package com.examly.springapp.service;


import java.util.List;

import com.examly.springapp.model.Crop;

public interface CropService {
    public Crop addCrop(Crop crop);
    public List<Crop> getCropByUserId(int userId);
    public Crop updateCrop(int cropId,Crop crop);
    public Crop deleteCrop(int cropId);
}
