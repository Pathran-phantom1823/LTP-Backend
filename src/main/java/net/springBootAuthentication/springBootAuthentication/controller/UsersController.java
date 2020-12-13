package net.springBootAuthentication.springBootAuthentication.controller;

import java.util.List;

import net.springBootAuthentication.springBootAuthentication.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomTranslators;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomUserInterface;
import net.springBootAuthentication.springBootAuthentication.customModel.Register;
import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;
import net.springBootAuthentication.springBootAuthentication.repository.RegisterRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ltp")
public class UsersController {
    
    @Autowired
    private RegisterRepository registerRepository;

    @GetMapping(value="/getUsers")
    public List<RegisterModel> getMethodName() {
        return registerRepository.findAll();
    }
    
    
    @PostMapping(value="get-translators")
    public ResponseEntity<?> getTranslators(@RequestBody RegisterModel entity) {
        Long id = entity.getId();
        List<CustomTranslators> list = registerRepository.getAllTranslators(id);
        return ResponseEntity.ok(list);
    }
    @PostMapping(value="get-agency-translators")
    public ResponseEntity<?> getAgencyTranslators(@RequestBody RegisterModel entity) {
        Long id = entity.getId();
        List<CustomTranslators> list = registerRepository.getAgencyTranslators(id);
        return ResponseEntity.ok(list);
    }

    @PostMapping(value="/checkUsernameExistence")
    public String checkUsernameExist(@RequestBody RegisterModel entity) {
        String username = entity.getUsername();
        String res = registerRepository.checkUsernameExist(username);
        if(res != null){
            return "Username is Unavailable";
        }else{
            return "Username is available";
        }
    }

    @PostMapping(value="/checkEmailExistence")
    public String checkEmailExist(@RequestBody RegisterModel entity) {
        String email = entity.getEmail();
        String res = registerRepository.checkEmailExist(email);
        if(res != null){
            return "Email is Unavailable";
        }else{
            return "Email is available";
        }
    }

    @GetMapping(value="/getAllUsers")
    public List<CustomUserInterface> getAllUsers() {
        List<CustomUserInterface> list = registerRepository.getAllUser();
        return list;
    }

    @PostMapping(value = "/disableUser")
    public ResponseEntity<?>disableUser(@RequestBody RegisterModel entity)throws  ResourceNotFoundException{
        try {
            String username = entity.getUsername();
            String disable = entity.getIsDisabled();
            System.out.println(username);
            System.out.println(disable);
            registerRepository.accountDisableEnable(username, disable);
        }catch (Exception e){
            return ResponseEntity.ok(e);
        }
        return ResponseEntity.ok("account disabled");

    }

    @PostMapping("/updateaccount")
    public ResponseEntity<?> updateAccount(@RequestBody RegisterModel entity)throws  ResourceNotFoundException{
        try {
            Long id = entity.getId();
            RegisterModel register = registerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
            register.setUsername(entity.getUsername());
            register.setEmail(entity.getEmail());
            registerRepository.save(register);
            return  ResponseEntity.ok("Account  Updated");
        }
        catch (Exception e){
            return  ResponseEntity.ok(e);
        }
    }

    @PostMapping("/checkIfPasswordCorrect")
    public ResponseEntity<?> PasswordCorrect(@RequestBody RegisterModel entity)throws  ResourceNotFoundException{
        try {
            Long id = entity.getId();
            RegisterModel register = registerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
            String currentPass = register.getPassword();
            String comparePass = entity.getPassword();
            if(new BCryptPasswordEncoder().matches(comparePass, currentPass)){
                ResponseEntity.ok(true);
            }else{
                ResponseEntity.ok(false);
            }
            return  ResponseEntity.ok("Account  Updated");
        }
        catch (Exception e){
            return  ResponseEntity.ok(e);
        }
    }

    @PostMapping("/updatepassword")
    public ResponseEntity<?> updatePassword(@RequestBody RegisterModel entity)throws  ResourceNotFoundException{
        try {
            Long id = entity.getId();
            RegisterModel register = registerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
            register.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
            registerRepository.save(register);
            return  ResponseEntity.ok("Account  Updated");
        }
        catch (Exception e){
            return  ResponseEntity.ok(e);
        }
    }
    
    
}
