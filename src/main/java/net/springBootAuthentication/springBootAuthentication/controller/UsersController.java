package net.springBootAuthentication.springBootAuthentication.controller;

import java.util.List;

import net.springBootAuthentication.springBootAuthentication.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        List<String> res = registerRepository.checkUsernameExist(username);
        if(!res.isEmpty()){
            return "Username is Unavailable";
        }else{
            return "Username is available";
        }
    }

    @PostMapping(value="/checkEmailExistence")
    public String checkEmailExist(@RequestBody RegisterModel entity) {
        String email = entity.getEmail();
        List<String> res = registerRepository.checkEmailExist(email);
        if(!res.isEmpty()){
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
    
    
}
