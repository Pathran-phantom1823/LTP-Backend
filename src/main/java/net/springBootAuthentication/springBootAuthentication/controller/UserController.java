package net.springBootAuthentication.springBootAuthentication.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import net.springBootAuthentication.springBootAuthentication.customModel.Register;
import net.springBootAuthentication.springBootAuthentication.model.AuthenticationRequest;
import net.springBootAuthentication.springBootAuthentication.model.Registration;
import net.springBootAuthentication.springBootAuthentication.model.Role;
import net.springBootAuthentication.springBootAuthentication.repository.RoleRepository;
import net.springBootAuthentication.springBootAuthentication.repository.UserRepository;
import net.springBootAuthentication.springBootAuthentication.services.AccountDetailsService;
import net.springBootAuthentication.springBootAuthentication.utility.JwtUtil;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private RoleRepository roleRepository;

    @Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationmanager;

	@Autowired
	private AccountDetailsService userDetailsService;

	@Autowired
    private JwtUtil jwtTokenUtil;
    
    	// Register==========================================================
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody Registration registration) {
		Registration register = new Registration();
		register.setEmail(registration.getEmail());
		register.setUsername(registration.getUsername());
		register.setPassword(new BCryptPasswordEncoder().encode(registration.getPassword()));
		register.setRoleId(registration.getRoleId());
		register.setExpired("false");
		register.setIsMember(registration.getIsMember());
		userRepository.save(register);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(registration.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		ArrayList<Object> list = new ArrayList<>();
		list.add(jwt);
		list.add(register);

		return ResponseEntity.ok(list);
	}

	@PostMapping(value="/registers")
	public ResponseEntity<?> postMethodName(@RequestBody Register entity) {
		Registration registration = new Registration();
		Role role = new Role();
		LocalDate date = LocalDate.now();
		role.setName(entity.getName());
		roleRepository.save(role);
		roleRepository.flush();

		registration.setUsername(entity.getUsername());
		registration.setEmail(entity.getEmail());
		registration.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
		registration.setisDisabled("false");
		registration.setExpired("false");
		registration.setCreatedAt(date);
		registration.setRoleId(role.getId());
		
		userRepository.save(registration);

		final UserDetails userDetails = userDetailsService.loadUserByUsername(registration.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		ArrayList<Object> list = new ArrayList<>();
		list.add(jwt);
		list.add(registration);
		
		return ResponseEntity.ok(list);
	}
	
    // ======================================================================
    
    @PostMapping("/login/")
	public ResponseEntity<?> loginUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationmanager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password");
		}

		return ResponseEntity.ok("User Login SuccessFully");
	}
    
}
