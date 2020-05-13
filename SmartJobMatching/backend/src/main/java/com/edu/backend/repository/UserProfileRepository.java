package com.edu.backend.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edu.backend.model.UserProfile;

@Repository
@Transactional
public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {
	
	@Query(value = "SELECT * FROM profiles wk  WHERE wk.profile_id =(:profileId)", nativeQuery = true)
	UserProfile getUserProfile(@Param("profileId") Long profileId);
	
	@Query(value = "SELECT * FROM profiles wk  WHERE wk.user_id =(:userId)", nativeQuery = true)
	UserProfile getUserProfileByUserId(@Param("userId") Long userId);
}