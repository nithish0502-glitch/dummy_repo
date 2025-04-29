package com.examly.springapp.service;

import com.examly.springapp.model.Membership;
import java.util.List;

public interface MembershipService {
    Membership addMembership(Long gymId, Membership membership);
    List<Membership> getMembershipsByGymId(Long gymId);
    Membership renewMembership(Long membershipId, String newEndDate);
    List<Membership> getExpiredMemberships();
}
