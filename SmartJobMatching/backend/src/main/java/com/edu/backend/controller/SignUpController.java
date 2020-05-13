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
import com.edu.backend.model.Response;
import com.edu.backend.service.impl.CredentialServiceImpl;
import com.edu.backend.utils.JSONUtils;

/**
 * Servlet implementation class SignUpController
 */
@WebServlet(description = "The Servlet manages Signup process", urlPatterns = { "/signup" })
public class SignUpController extends GenericController {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CredentialServiceImpl credentialService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUpController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Credential> credentials = credentialService.getAllCredentials();

		String jsonResponse = null;

		if (credentials.size() > 0)
			jsonResponse = JSONUtils.toJson(credentials);
		else
			jsonResponse = JSONUtils.toJson(new Response("No credentials found"));
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
			Credential credential = JSONUtils.fromJson(requestJSON, Credential.class);
			Credential existingUser = this.credentialService.getCredential(credential.getUsername());

			String jsonResponse = null;
			
			if (existingUser != null) {
				jsonResponse = JSONUtils.toJson(new Response("Username is already in use"));
			} else {
				credential = this.credentialService.addCredential(credential);
				jsonResponse = JSONUtils.toJson(new Response(credential.getUserId(), "User has been created successfully"));
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