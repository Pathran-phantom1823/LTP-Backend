package net.springBootAuthentication.springBootAuthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomJobs;
import net.springBootAuthentication.springBootAuthentication.model.OrgMembers;


@Repository
public interface orgMemberRepository extends JpaRepository<OrgMembers, Long>{

    @Transactional
	@Modifying
    @Query(value = "{call getMembers(:id)}", nativeQuery = true)
    List<OrgMembers> getMembers(@Param("id") Long id);

    @Transactional
	@Modifying
    @Query(value = "{call deleteMemberById(:id)}" , nativeQuery = true)
    void deleteMemberByid(@Param("id") Long id);

    @Transactional
	@Modifying
    @Query(value = "{call getRoleType()}", nativeQuery = true)
    Long getRoleType();

    @Transactional
	@Modifying
    @Query(value = "call updateMember(:id, :username, :email, :account_type, :expired, :is_member)", nativeQuery = true)
    void updateMembers(@Param("id") long id, @Param("username") String username, @Param("email") String email, @Param("account_type") String account_type, @Param("expired") Boolean expired, @Param("is_member") Boolean is_member);

    @Transactional
	@Modifying
    @Query(value = "{call getMyAssignedJobs(:id)}", nativeQuery = true)
    List<CustomJobs> getMyAssignedJobs(@Param("id") Long id);
}
