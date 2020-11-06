package net.springBootAuthentication.springBootAuthentication.controller;

import org.springframework.http.MediaType;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomAdminProfile;
import net.springBootAuthentication.springBootAuthentication.model.AddressModel;
import net.springBootAuthentication.springBootAuthentication.model.AdminProfileModel;
import net.springBootAuthentication.springBootAuthentication.repository.AddressRepository;
import net.springBootAuthentication.springBootAuthentication.repository.AdminProfileRepository;


@RestController
@RequestMapping("/ltp")
public class AdminProfileController {
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private AdminProfileRepository adminProfileRepository;

    @Autowired
    private AddressRepository addressRepository;
    

    @RequestMapping(value = "/createAdminProfile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> postJob(@RequestPart(value = "data") String data,
            @RequestPart(value = "img") final MultipartFile img) throws IOException {


        try {
            
            AddressModel addressModel = new AddressModel();
            AdminProfileModel adminModel = new AdminProfileModel();
            LocalDate date = LocalDate.now();

            CustomAdminProfile customAdminProfile = objectMapper.readValue(data, CustomAdminProfile.class);
            String tempImageName = img.getOriginalFilename();
            String imageName = tempImageName.replaceAll("\\s+", "_");
            File convertfile = new File(
                    "src/main/resources/img/" + String.format("%d%s%s", customAdminProfile.getAccountId(), date, imageName));
            convertfile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertfile);
            fout.write(img.getBytes());
            fout.close();

            addressModel.setCity(customAdminProfile.getCity());
            addressModel.setCountry(customAdminProfile.getCountry());
            addressModel.setPostal(customAdminProfile.getPostalcode());
            addressModel.setStreet(customAdminProfile.getStreet());
            addressModel.setZipcode(customAdminProfile.getZipcode());
            addressRepository.saveAndFlush(addressModel);
            // System.out.println(customAdminProfile.getBirthdate());
            adminModel.setAddressId(addressModel.getId());
            adminModel.setAccountId(customAdminProfile.getAccountId());
            adminModel.setAge(customAdminProfile.getAge());
            adminModel.setBirthdate(customAdminProfile.getBirthdate());
            adminModel.setFirstname(customAdminProfile.getFirstname());
            adminModel.setLastname(customAdminProfile.getLastname());
            adminModel.setGender(customAdminProfile.getGender());
            adminModel.setEmail(customAdminProfile.getEmail());
            adminModel.setPhonenumber(customAdminProfile.getPhonenumber());
            adminModel.setImage(String.format("%d%s%s", customAdminProfile.getAccountId(), date, imageName));
            adminProfileRepository.saveAndFlush(adminModel);

            List <Object> lists = new ArrayList<>();

            lists.add(addressModel);
            lists.add(adminModel);
            return ResponseEntity.ok(lists);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        
    }
}
