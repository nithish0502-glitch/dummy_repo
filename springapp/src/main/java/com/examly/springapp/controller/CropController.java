package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Crop;
import com.examly.springapp.service.CropService;

@RestController
public class CropController {
    @Autowired
    CropService cropService;

    @PostMapping("/api/crop")
    public ResponseEntity<Crop> addCrop(@RequestBody Crop crop){
        Crop newCrop=cropService.addCrop(crop);
        if(newCrop!=null)
        return new ResponseEntity<>(newCrop,HttpStatus.CREATED);
        else
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/api/crop")
    public ResponseEntity<Crop> updateCrop(@PathVariable int cropId, @RequestBody Crop crop){
        Crop updatedCrop = cropService.updateCrop(cropId, crop);
        if(updatedCrop!=null)
        return new ResponseEntity<>(updatedCrop,HttpStatus.CREATED);
        else
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/api/crop/{userId}")
    public ResponseEntity<List<Crop>> getCropByUserId(@PathVariable int userId){
        List<Crop> crops=cropService.getCropByUserId(userId);
        if(crops!=null)
            return new ResponseEntity<>(crops,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
