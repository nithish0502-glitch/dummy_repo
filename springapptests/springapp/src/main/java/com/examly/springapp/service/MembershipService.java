package com.examly.springapp.service;

import com.examly.springapp.model.Membership;

import java.time.LocalDate;
import java.util.List;

public interface MembershipService {
        Membership addMembership(Long gymId, Membership membership);
        List<Membership> getMembershipsByGymId(Long gymId);
        Membership renewMembership(Long membershipId, Membership membership);
        List<Membership> getExpiredMemberships();
        //LocalDate parsedDate = LocalDate.parse(newEndDate);
}
