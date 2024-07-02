package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.examly.springapp.model.DietPlan;
import com.examly.springapp.service.AgroChemicalService;

@RestController
public class DietPlanController {

    @Autowired
    private AgroChemicalService agroChemicalService;

    @PostMapping("/api/agrochemical")
    @PreAuthorize("hasAuthority('SELLER')")
    public ResponseEntity<DietPlan> createAgroChemical(@RequestBody DietPlan agroChemical) {
        DietPlan createdAgroChemical = agroChemicalService.createAgroChemical(agroChemical);
        return new ResponseEntity<>(createdAgroChemical,HttpStatus.CREATED);
    }

    @GetMapping("/api/agrochemical/{id}")
    @PreAuthorize("hasAnyAuthority('SELLER', 'FARMER')")
    public ResponseEntity<DietPlan> getAgroChemicalById(@PathVariable int id) {
        DietPlan agroChemical = agroChemicalService.getAgroChemicalById(id);
        return ResponseEntity.ok(agroChemical);
    }

    @GetMapping("/api/agrochemical")
    @PreAuthorize("hasAnyAuthority('SELLER', 'FARMER')")
    public ResponseEntity<List<DietPlan>> getAllAgroChemicals() {
        List<DietPlan> agroChemicals = agroChemicalService.getAllAgroChemicals();
        if(agroChemicals != null) {
            return new ResponseEntity<>(agroChemicals, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/api/agrochemical/{id}")
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public ResponseEntity<DietPlan> updateAgroChemical(@PathVariable int id, @RequestBody DietPlan agroChemical) {
        DietPlan updatedAgroChemical = agroChemicalService.updateAgroChemical(id, agroChemical);
        return ResponseEntity.ok(updatedAgroChemical);
    }

    @DeleteMapping("/api/agrochemical/{id}")
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public ResponseEntity<DietPlan> deleteAgroChemical(@PathVariable int id) {
        DietPlan deleted = agroChemicalService.deleteAgroChemical(id);
        return new ResponseEntity<>(deleted,HttpStatus.OK);
    }
}
 