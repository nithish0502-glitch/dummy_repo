package com.examly.springapp.service;

import com.examly.springapp.model.Gym;
import com.examly.springapp.model.Membership;
import com.examly.springapp.repository.GymRepo;
import com.examly.springapp.repository.MembershipRepo;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    private MembershipRepo membershipRepo;

    @Autowired
    private GymRepo gymRepo;

    @Override
    public Membership addMembership(Long gymId, Membership membership) {
        Gym gym = gymRepo.findById(gymId).orElseThrow(() ->
            new EntityNotFoundException("Gym not found with ID: " + gymId)
        );
        membership.setGym(gym);
        return membershipRepo.save(membership);
    }

    @Override
    public Membership renewMembership(Long membershipId, Membership membership) {
        Membership oldMembership = membershipRepo.findById(membershipId)
            .orElseThrow(() -> new EntityNotFoundException("Membership not found"));
    
        // Renew only if expired
        if (oldMembership.getEndDate().isBefore(LocalDate.now())) {
            // Update the end date of the existing membership
            oldMembership.setEndDate(membership.getEndDate());
            return membershipRepo.save(oldMembership);
        } else {
            // If not expired, throw an exception or handle it based on your requirement
            throw new IllegalStateException("Membership is still active and does not need renewal.");
        }
    }
    

    @Override
    public List<Membership> getMembershipsByGymId(Long gymId) {
        return membershipRepo.findByGymId(gymId);
    }

    @Override
    public List<Membership> getExpiredMemberships() {
        return membershipRepo.findByEndDateBefore(LocalDate.now());
    }
   
}
