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

import com.edu.backend.model.Credential;
import com.edu.backend.model.Job;
import com.edu.backend.model.JobResponse;
import com.edu.backend.model.UserProfile;
import com.edu.backend.service.CredentialService;
import com.edu.backend.service.JobService;
import com.edu.backend.service.UserProfileService;
import com.edu.backend.utils.JSONUtils;
import com.edu.backend.utils.JobDTOUtil;
import com.edu.backend.utils.JobMatchingUtil;

import io.swagger.annotations.Api;

/**
 * Servlet implementation class SignInController
 */
@WebServlet(description = "The Servlet manages Signup process", urlPatterns = {"/jobs"})
@Api(tags = "SignIn Controller", value="SignIn Controller")
public class JobController extends GenericController {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CredentialService credentialService;

	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private JobService jobService;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JobController() {
		super();
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
			Credential credential = JSONUtils.fromJson(requestJSON, Credential.class);

			String jsonResponse = null;
			
			List<Job> jobs = jobService.getAllJobs();
			
			UserProfile profile = userProfileService.getUserProfileByUserId(credential.getUserId());
			
			if (profile != null) {
				jobs = JobMatchingUtil.matching(profile, jobs);
				jsonResponse = JSONUtils.toJson(new JobResponse(credential.getUserId(), true, JobDTOUtil.getJobDTOs(jobs), "User already has a profile"));
			} else {
				jsonResponse = JSONUtils.toJson(new JobResponse(credential.getUserId(), false, JobDTOUtil.getJobDTOs(jobs), "User has not created the profile"));
			}				 
			 
			System.out.println("New Credentials Added: " + jsonResponse);
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