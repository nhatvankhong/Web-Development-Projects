package com.edu.backend.service;

import java.util.List;

import com.edu.backend.model.UserProfile;

public interface UserProfileService {

	void deleteUserProfile(UserProfile userProfile);
	
	UserProfile updateUserProfile(UserProfile userProfile);
	
	UserProfile getUserProfile(Long profileId);
	
	List<UserProfile> getAllUserProfiles();
	
	UserProfile addUserProfile(UserProfile userProfile);
	
	UserProfile getUserProfileByUserId(Long userId);
}