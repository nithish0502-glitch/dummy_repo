package com.examly.springapp.repository;

import com.examly.springapp.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MembershipRepo extends JpaRepository<Membership, Long> {
 
    @Query("SELECT m FROM Membership m WHERE m.gym.id = :gymId")
    List<Membership> findByGymId(Long gymId);

    @Query("SELECT m FROM Membership m WHERE m.endDate < CURRENT_DATE")
    List<Membership> findExpiredMemberships();
    List<Membership> findByEndDateBefore(LocalDate date);
}
