package com.examly.springapp.service;

import com.examly.springapp.model.Membership;

import java.time.LocalDate;
import java.util.List;

public interface MembershipService {
        Membership addMembership(Long gymId, Membership membership);
        List<Membership> getMembershipsByGymId(Long gymId);
        Membership renewMembership(Long membershipId, String newEndDate);
        List<Membership> getExpiredMemberships();
        Membership renewMembership(Membership membership);
        //LocalDate parsedDate = LocalDate.parse(newEndDate);
}
