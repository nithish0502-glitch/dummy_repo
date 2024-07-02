package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.AgroChemical;
import com.examly.springapp.repository.AgroChemicalRepo;

@Service
public class AgroChemicalServiceImpl implements AgroChemicalService {
    @Autowired
    private AgroChemicalRepo agroChemicalRepository;

    @Override
    public AgroChemical createAgroChemical(AgroChemical agroChemical) {
        return agroChemicalRepository.save(agroChemical);
    }

    @Override
    public AgroChemical getAgroChemicalById(int id) {
        return agroChemicalRepository.findById(id).orElseThrow(() -> new RuntimeException("AgroChemical not found"));
    }

    @Override
    public List<AgroChemical> getAllAgroChemicals() {
        return agroChemicalRepository.findAll();
    }

    @Override
    public AgroChemical updateAgroChemical(int id, AgroChemical updatedAgroChemical) {
        AgroChemical existingAgroChemical = agroChemicalRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("AgroChemical not found"));

        existingAgroChemical.setName(updatedAgroChemical.getName());
        existingAgroChemical.setBrand(updatedAgroChemical.getBrand());
        existingAgroChemical.setCategory(updatedAgroChemical.getCategory());
        existingAgroChemical.setDescription(updatedAgroChemical.getDescription());
        existingAgroChemical.setUnit(updatedAgroChemical.getUnit());
        existingAgroChemical.setPricePerUnit(updatedAgroChemical.getPricePerUnit());
        existingAgroChemical.setImage(updatedAgroChemical.getImage());

        return agroChemicalRepository.save(existingAgroChemical);
    }

   @Override
public AgroChemical deleteAgroChemical(int id) {
    AgroChemical agroChemical = agroChemicalRepository.findById(id).orElse(null);
  
        agroChemicalRepository.deleteById(id);
        return agroChemical;
       
    }
}