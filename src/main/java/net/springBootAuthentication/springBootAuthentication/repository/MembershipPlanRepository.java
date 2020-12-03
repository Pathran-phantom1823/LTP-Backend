package net.springBootAuthentication.springBootAuthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.springBootAuthentication.springBootAuthentication.model.MembershipPlanModel;

public interface MembershipPlanRepository extends JpaRepository<MembershipPlanModel, Long> {

}
