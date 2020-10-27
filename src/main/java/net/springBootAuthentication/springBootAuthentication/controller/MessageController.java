package net.springBootAuthentication.springBootAuthentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.springBootAuthentication.springBootAuthentication.customModel.Register;
import net.springBootAuthentication.springBootAuthentication.exception.ResourceNotFoundException;
import net.springBootAuthentication.springBootAuthentication.model.MessageModel;
import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;
import net.springBootAuthentication.springBootAuthentication.repository.RegisterRepository;

@RestController
@RequestMapping("/ltp")
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpTemplate;

    @Autowired
    private RegisterRepository registerRepository;
    
    @MessageMapping("/ltp/(to)")
    public void sendmessage(@DestinationVariable Register to, MessageModel message)
            throws ResourceNotFoundException {
        System.out.println(message + "" + to.getId());
        RegisterModel exist = registerRepository.findById(to.getId()).orElseThrow(() -> new ResourceNotFoundException("not found"));
        if(exist == null){
            System.out.println("Null");
        }else{
            simpTemplate.convertAndSend("/topic/messages" + exist.getUsername(), message);
        }
    }
}
