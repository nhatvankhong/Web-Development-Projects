package com.edu.backend.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.edu.backend.model.AuthResponse;
import com.edu.backend.model.Credential;
import com.edu.backend.model.Response;
import com.edu.backend.model.UserProfile;
import com.edu.backend.service.CredentialService;
import com.edu.backend.service.UserProfileService;
import com.edu.backend.utils.JSONUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Servlet implementation class SignInController
 */
@WebServlet(description = "The Servlet manages Signup process", urlPatterns = {"/signin"})
@Api(tags = "SignIn Controller", value="SignIn Controller")
public class SignInController extends GenericController {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CredentialService credentialService;

	@Autowired
	private UserProfileService userProfileService;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignInController() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@ApiOperation(value = "/signin")
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
			Credential existingUser = this.credentialService.getCredential(credential.getUsername(), credential.getPassword());

			String jsonResponse = null;
			
			if (existingUser == null) {
				jsonResponse = JSONUtils.toJson(new Response("Invalid user authentication details"));
			} else {
				
				UserProfile profile = userProfileService.getUserProfile(existingUser.getUserId());
				
				if (profile != null) {
					jsonResponse = JSONUtils.toJson(new AuthResponse(existingUser.getUserId(), true, "User already has a profile"));
				} else {
					jsonResponse = JSONUtils.toJson(new AuthResponse(existingUser.getUserId(), false, "User has not created the profile"));
				}				
			}  
			 
			System.out.println("New Credentials Added: " + jsonResponse);
			sendResponseToClient(response, jsonResponse);			
		} catch (Exception ex) {
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