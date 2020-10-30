package net.springBootAuthentication.springBootAuthentication.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import net.springBootAuthentication.springBootAuthentication.customModel.Register;
import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;
import net.springBootAuthentication.springBootAuthentication.model.RoleModel;
import net.springBootAuthentication.springBootAuthentication.repository.RegisterRepository;
import net.springBootAuthentication.springBootAuthentication.repository.RoleRepository;

@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RegisterRepository registerRepository;

    @PostMapping("/register")

    public ResponseEntity<?> addAccount(@RequestBody Register entity) throws BadRequest {
        try {
            RegisterModel account = new RegisterModel();
            Integer roleId = registerRepository.getRoleIdByType(entity.getRoleType());

            LocalDate date = LocalDate.now();

            account.setUsername(entity.getUsername());
            account.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
            account.setEmail(entity.getEmail());
            account.setIsDisabled("false");
            account.setDateCreated(date);
            account.setExpired("false");
            account.setIsMember("false");
            account.setRoleid(roleId);

            registerRepository.save(account);

            ArrayList<Object> list = new ArrayList<>();

            list.add(account);

            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping("/addRole")
    public ResponseEntity<?> addRole(@RequestBody RoleModel data) {
        RoleModel roles = new RoleModel();
        roles.setRoleType(data.getRoleType());
        roleRepository.save(roles);

        return ResponseEntity.ok(roles);
    }
}
