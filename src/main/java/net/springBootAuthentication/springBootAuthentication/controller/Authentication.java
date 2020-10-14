package net.springBootAuthentication.springBootAuthentication.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.springBootAuthentication.springBootAuthentication.model.AuthenticationRequest;
import net.springBootAuthentication.springBootAuthentication.model.AuthenticationResponse;
import net.springBootAuthentication.springBootAuthentication.model.Response;
import net.springBootAuthentication.springBootAuthentication.services.AccountDetailsService;
import net.springBootAuthentication.springBootAuthentication.utility.JwtUtil;

@RestController
@RequestMapping("/ltp")
public class Authentication {
	
	@Autowired
	private AuthenticationManager authenticationmanager;
	
	@Autowired
	private AccountDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationmanager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}catch (BadCredentialsException e ) {
			ArrayList<StackTraceElement[]> err = new ArrayList<StackTraceElement[]>();
			err.add(e.getStackTrace());
			return ResponseEntity.ok(new Response(404, "Incorrect Username or Password", err));
		}
		final UserDetails userDetails  = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails); 
		ArrayList<HashMap<String, String>> res = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> obj = new HashMap<String, String>();
		obj.put("token", jwt);
		res.add(obj);
		return ResponseEntity.ok(new Response(404, "Successfully LogIn", res));
	}
}