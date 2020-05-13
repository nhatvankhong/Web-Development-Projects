package com.edu.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.backend.model.UserProfile;
import com.edu.backend.repository.UserProfileRepository;
import com.edu.backend.service.UserProfileService;

@Service("userProfileService")
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
	private UserProfileRepository userProfilelRepository;
	
	public UserProfileServiceImpl() {  }
	
	public UserProfile addUserProfile(UserProfile userProfile) {
		return this.userProfilelRepository.save(userProfile);
	}
	
	public List<UserProfile> getAllUserProfiles() {
		return (List<UserProfile>)userProfilelRepository.findAll();
	}
	
	public void deleteUserProfile(UserProfile userProfile) {
		userProfilelRepository.delete(userProfile);
	}

	public UserProfile updateUserProfile(UserProfile userProfile) {
		return userProfilelRepository.save(userProfile);
	}

	@Override
	public UserProfile getUserProfile(Long profileId) {
		return userProfilelRepository.getUserProfile(profileId);
	}

	@Override
	public UserProfile getUserProfileByUserId(Long userId) {
		return userProfilelRepository.getUserProfileByUserId(userId);
	}
}