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
import com.examly.springapp.model.AgroChemical;
import com.examly.springapp.service.AgroChemicalService;

@RestController
public class AgroChemicalController {

    @Autowired
    private AgroChemicalService agroChemicalService;

    @PostMapping("/api/agrochemical")
    @PreAuthorize("hasAuthority('SELLER')")
    public ResponseEntity<AgroChemical> createAgroChemical(@RequestBody AgroChemical agroChemical) {
        AgroChemical createdAgroChemical = agroChemicalService.createAgroChemical(agroChemical);
        return new ResponseEntity<>(createdAgroChemical,HttpStatus.CREATED);
    }

    @GetMapping("/api/agrochemical/{id}")
    @PreAuthorize("hasAnyAuthority('SELLER', 'FARMER')")
    public ResponseEntity<AgroChemical> getAgroChemicalById(@PathVariable int id) {
        AgroChemical agroChemical = agroChemicalService.getAgroChemicalById(id);
        return ResponseEntity.ok(agroChemical);
    }

    @GetMapping("/api/agrochemical")
    @PreAuthorize("hasAnyAuthority('SELLER', 'FARMER')")
    public ResponseEntity<List<AgroChemical>> getAllAgroChemicals() {
        List<AgroChemical> agroChemicals = agroChemicalService.getAllAgroChemicals();
        return ResponseEntity.ok(agroChemicals);
    }

    @PutMapping("/api/agrochemical/{id}")
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public ResponseEntity<AgroChemical> updateAgroChemical(@PathVariable int id, @RequestBody AgroChemical agroChemical) {
        AgroChemical updatedAgroChemical = agroChemicalService.updateAgroChemical(id, agroChemical);
        return ResponseEntity.ok(updatedAgroChemical);
    }

    @DeleteMapping("/api/agrochemical/{id}")
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public ResponseEntity<Void> deleteAgroChemical(@PathVariable int id) {
        agroChemicalService.deleteAgroChemical(id);
        return ResponseEntity.noContent().build();
    }
}
