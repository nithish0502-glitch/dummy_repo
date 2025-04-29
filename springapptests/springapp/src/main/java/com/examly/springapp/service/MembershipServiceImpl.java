package com.examly.springapp.service;

import com.examly.springapp.model.Gym;
import com.examly.springapp.model.Membership;
import com.examly.springapp.repository.GymRepo;
import com.examly.springapp.repository.MembershipRepo;
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
        Gym gym = gymRepo.findById(gymId).orElseThrow();
        membership.setGym(gym);
        return membershipRepo.save(membership);
    }

    @Override
    public List<Membership> getMembershipsByGymId(Long gymId) {
        return membershipRepo.findByGymId(gymId);
    }

    @Override
    public Membership renewMembership(Long membershipId, String newEndDate) {
        Membership membership = membershipRepo.findById(membershipId).orElseThrow();
        if (membership.getEndDate().isBefore(LocalDate.now())) {
            throw new IllegalStateException("Membership has expired and cannot be renewed.");
        }
        membership.setEndDate(LocalDate.parse(newEndDate));
        return membershipRepo.save(membership);
    }

    @Override
    public List<Membership> getExpiredMemberships() {
        return membershipRepo.findExpiredMemberships();
    }
}
