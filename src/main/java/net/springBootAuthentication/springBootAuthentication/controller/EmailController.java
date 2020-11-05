package net.springBootAuthentication.springBootAuthentication.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.springBootAuthentication.springBootAuthentication.config.EmailConfig;
import net.springBootAuthentication.springBootAuthentication.customModel.Register;
import net.springBootAuthentication.springBootAuthentication.exception.ResourceNotFoundException;
import net.springBootAuthentication.springBootAuthentication.model.EmailModel;
import net.springBootAuthentication.springBootAuthentication.model.MailRequest;
import net.springBootAuthentication.springBootAuthentication.model.MailResponse;
import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;
import net.springBootAuthentication.springBootAuthentication.repository.RegisterRepository;
import net.springBootAuthentication.springBootAuthentication.services.EmailService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    RegisterRepository registerRepository;
    
    @Autowired
    private EmailService emailService;

    @PostMapping(value="/send-email")
    public MailResponse sendEmail(@RequestBody MailRequest entity) {
        Map<String, Object> model = new HashMap<>();
        model.put("Name", entity.getTo());
        
        return emailService.sendMail(entity, model);
    }


    @PostMapping(value="/reset-password")
    public ResponseEntity<?> ResetPassword(@RequestBody RegisterModel entity)throws ResourceNotFoundException{
        Long id = registerRepository.getEmail(entity.getEmail());
        RegisterModel accounts = registerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("NOt Found"));
        try {
            accounts.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
        } catch (Exception e) {
           return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR); 
        }
        registerRepository.save(accounts);
        return ResponseEntity.ok("Password is Reset");
    }
    
    
}
