package com.examly.springapp.controller;

import com.examly.springapp.model.Membership;
import com.examly.springapp.service.MembershipService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/membership")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @PostMapping
    public ResponseEntity<Membership> addMembership(@RequestBody Membership membership) {
        Membership saved = membershipService.renewMembership(membership);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved); // Ensure saved has memberName set
    }
    

    @GetMapping("/search/{gymId}")
    public ResponseEntity<?> getMembershipsByGym(@PathVariable Long gymId) {
        List<Membership> memberships = membershipService.getMembershipsByGymId(gymId);
        if (memberships.isEmpty()) {
            return ResponseEntity.status(204).body("No memberships found for this gym.");
        }
        return ResponseEntity.ok(memberships);
    }

    @PostMapping("/renew/{membershipId}")
    public ResponseEntity<?> renewMembership(@PathVariable Long membershipId, @RequestBody Map<String, String> request) {
   try {
    String newEndDate = null;
        Membership renewed = membershipService.renewMembership(membershipId, newEndDate);
    return ResponseEntity.ok(renewed);
} catch (IllegalStateException e) {
    return ResponseEntity.badRequest().body(e.getMessage()); // 400 if expired
} catch (EntityNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Membership not found."); // 404 if not found
}
}



    @GetMapping("/expired")
    public ResponseEntity<?> getExpiredMemberships() {
        List<Membership> expired = membershipService.getExpiredMemberships();
        if (expired.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(expired);
    }
}
