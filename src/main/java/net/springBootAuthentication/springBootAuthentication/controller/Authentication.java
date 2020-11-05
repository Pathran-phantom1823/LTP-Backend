package net.springBootAuthentication.springBootAuthentication.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import net.springBootAuthentication.springBootAuthentication.model.AuthenticationRequest;
import net.springBootAuthentication.springBootAuthentication.model.AuthenticationResponse;
import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;
import net.springBootAuthentication.springBootAuthentication.model.Response;
import net.springBootAuthentication.springBootAuthentication.model.RoleModel;
import net.springBootAuthentication.springBootAuthentication.repository.LoginRepository;
import net.springBootAuthentication.springBootAuthentication.repository.RoleRepository;
import net.springBootAuthentication.springBootAuthentication.services.AccountDetails;
import net.springBootAuthentication.springBootAuthentication.services.AccountDetailsService;
import net.springBootAuthentication.springBootAuthentication.utility.JwtUtil;

@RestController
@RequestMapping("/api")
public class Authentication {
	
	@Autowired
	private AuthenticationManager authenticationmanager;
	
	@Autowired
	private AccountDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private LoginRepository check;

	@Autowired
	private RoleRepository rolerepository;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		final Long id;
		final String roleName;
		try {
			RegisterModel info = check.findByusername(authenticationRequest.getUsername());
			Long roleId = info.getRoleid();
			RoleModel role = rolerepository.findById(roleId).orElseThrow(() -> new ResourceAccessException("not Found"));
			roleName = role.getRoleType();
			id = info.getId();
			authenticationmanager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}catch (BadCredentialsException e ) {
			ArrayList<StackTraceElement[]> err = new ArrayList<StackTraceElement[]>();
			err.add(e.getStackTrace());
			return ResponseEntity.ok(new Response(404, "Incorrect Username or Password", err));
		}catch (NullPointerException e) {
			ArrayList<StackTraceElement[]> err = new ArrayList<StackTraceElement[]>();
			err.add(e.getStackTrace());
			return ResponseEntity.ok(new Response(404, "Incorrect Username or Password", err));
		}
		final UserDetails userDetails  = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails); 
		ArrayList<HashMap<String, String>> res = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> obj = new HashMap<String, String>();
		obj.put("token", jwt);
		obj.put("id", id.toString());
		obj.put("roleType", roleName);
		res.add(obj);
		return ResponseEntity.ok(new Response(200, "Successfully LogIn", res));
	}

	 @PostMapping(value="/verify")
	 public ResponseEntity<?> postMethodName(@RequestBody AuthenticationRequest entity) {
	 	final String roleName;
	 	try {
	 		RegisterModel info = check.findByusername(entity.getUsername());
	 		Long roleId = info.getRoleid();
	 		RoleModel role = rolerepository.findById(roleId).orElseThrow(() -> new ResourceAccessException("not Found"));
	 		roleName = role.getRoleType();
	 	} catch (Exception e) {
	 		return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
	 	}
	 	return ResponseEntity.ok(roleName);
	 }
	
	
}
