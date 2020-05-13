package com.edu.backend.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.edu.backend.model.Job;
import com.edu.backend.model.JobResponse;
import com.edu.backend.model.Response;
import com.edu.backend.model.Skill;
import com.edu.backend.model.Technology;
import com.edu.backend.model.UserProfile;
import com.edu.backend.model.dto.UserProfileDTO;
import com.edu.backend.service.JobService;
import com.edu.backend.service.SkillService;
import com.edu.backend.service.TechnologyService;
import com.edu.backend.service.UserProfileService;
import com.edu.backend.utils.JSONUtils;
import com.edu.backend.utils.JobDTOUtil;
import com.edu.backend.utils.JobMatchingUtil;

/**
 * Servlet implementation class UserProfileController
 */
@WebServlet(description = "The Servlet manages user profile form", urlPatterns = { "/user-profile" })
public class UserProfileController extends GenericController {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private SkillService skillService;
	
	@Autowired
	private TechnologyService technologyService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long userId = Long.valueOf(request.getParameter("userId")); //user-profile?userId=1
		
		UserProfile profile = userProfileService.getUserProfileByUserId(userId);

		String jsonResponse = null;

		if (profile != null)
			jsonResponse = JSONUtils.toJson(new UserProfileDTO(profile));
		else
			jsonResponse = JSONUtils.toJson(new Response("No user profile found", userId));
		System.out.println(jsonResponse);
		sendResponseToClient(response, jsonResponse);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer stringBuffer = new StringBuffer();
		String line = null;
		try(BufferedReader bufferedReader = request.getReader()) {
			while((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
			}

			String requestJSON = stringBuffer.toString();
			System.out.println(requestJSON);
			UserProfile userProfile = JSONUtils.fromJson(requestJSON, UserProfile.class);
			
			for (Skill skill : userProfile.getSkills()) {
				skill.setSkillId(skillService.getSkill(skill.getName_level()).getSkillId());
			}
			
			for (Technology technology : userProfile.getTechnologies()) {
				technology.setTechId(technologyService.getTechnology(technology.getName_level()).getTechId());
			}
			
			//Update UserProfile table
			UserProfile existingUserProfile = userProfileService.getUserProfileByUserId(userProfile.getCredential().getUserId());
			if (existingUserProfile != null) {
				userProfile.setProfileId(existingUserProfile.getProfileId());
				userProfile = userProfileService.updateUserProfile(userProfile);
			}
			else {
				userProfile = userProfileService.addUserProfile(userProfile);
			}
			
			//Execute job matching
			List<Job> jobs = jobService.getAllJobs();
			List<Job> matchedJobs = JobMatchingUtil.matching(userProfile, jobs);
			
			//Send JSON response
			String jsonResponse = JSONUtils.toJson(new JobResponse(userProfile.getCredential().getUserId(), true, JobDTOUtil.getJobDTOs(matchedJobs), "List of matched jobs"));
			
			System.out.println("Matched Jobs for UserID " + userProfile.getCredential().getUserId() + ": " + jsonResponse);
			
			sendResponseToClient(response, jsonResponse);
		} catch (Exception ex) {
			ex.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println(ex.toString());
		}
	}

	private void sendResponseToClient(HttpServletResponse response, String jsonResponse) throws IOException {
		setAccessControlHeaders(response);
		response.setContentType("application/json");
		response.setBufferSize(4096);
		response.getWriter().println(jsonResponse);
	}
}