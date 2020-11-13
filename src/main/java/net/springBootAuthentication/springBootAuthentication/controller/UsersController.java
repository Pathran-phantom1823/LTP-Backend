package net.springBootAuthentication.springBootAuthentication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomTranslators;
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
    
}
