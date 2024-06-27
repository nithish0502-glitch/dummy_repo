package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.AgroChemical;

public interface AgroChemicalService {
    AgroChemical createAgroChemical(AgroChemical agroChemical);
    AgroChemical getAgroChemicalById(int id);
    List<AgroChemical> getAllAgroChemicals();
    AgroChemical updateAgroChemical(int id, AgroChemical agroChemical);
    void deleteAgroChemical(int id);
}
