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
    public Membership renewMembership(Long membershipId, String newEndDate) {
        Membership membership = membershipRepo.findById(membershipId)
            .orElseThrow(() -> new EntityNotFoundException("Membership not found"));

        if (membership.getEndDate().isBefore(LocalDate.now())) {
            throw new IllegalStateException("Cannot renew an expired membership.");
        }

        membership.setEndDate(LocalDate.parse(newEndDate));
        return membershipRepo.save(membership);
    }

    @Override
    public List<Membership> getMembershipsByGymId(Long gymId) {
        return membershipRepo.findByGymId(gymId);
    }

    @Override
    public List<Membership> getExpiredMemberships() {
        return membershipRepo.findByEndDateBefore(LocalDate.now());
    }
    @Override
    public Membership renewMembership(Membership membership) {
        return membershipRepo.save(membership); // Optional behavior if used elsewhere
    }
}
