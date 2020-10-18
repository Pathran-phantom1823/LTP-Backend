package net.springBootAuthentication.springBootAuthentication.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.springBootAuthentication.springBootAuthentication.customModel.Register;
import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;
import net.springBootAuthentication.springBootAuthentication.model.RoleModel;
import net.springBootAuthentication.springBootAuthentication.repository.RegisterRepository;
import net.springBootAuthentication.springBootAuthentication.repository.RoleRepository;
import net.springBootAuthentication.springBootAuthentication.services.AccountDetailsService;
import net.springBootAuthentication.springBootAuthentication.utility.JwtUtil;

@RestController
@RequestMapping("/api")
public class RegisterController {


    @Autowired
    private AccountDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RegisterRepository registerRepository;

    @PostMapping("/register")
    public ResponseEntity<?> addAccount(@RequestBody Register entity) {
        RegisterModel account = new RegisterModel();
        RoleModel role = new RoleModel();
        LocalDate date  = LocalDate.now();

        role.setRoleType(entity.getRoleType());
        roleRepository.save(role);
        roleRepository.flush();

        account.setUsername(entity.getUsername());
        account.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
        account.setEmail(entity.getEmail());
        account.setIsDisabled("false");
		account.setIsMember("false");
        account.setRoleid(role.getId());
        account.setDateCreated(date);
        account.setExpired("false");
        registerRepository.save(account);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(account.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        ArrayList<Object> list = new ArrayList<>();
        list.add(jwt);
        list.add(account);
        

        return ResponseEntity.ok(list);

    }
}
