package net.springBootAuthentication.springBootAuthentication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;
import net.springBootAuthentication.springBootAuthentication.repository.RegisterRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/ltp")
public class UsersController {
    
    @Autowired
    private RegisterRepository registerRepository;

    @GetMapping(value="/getUsers")
    public List<RegisterModel> getMethodName() {
        return registerRepository.findAll();
    }
    
}
