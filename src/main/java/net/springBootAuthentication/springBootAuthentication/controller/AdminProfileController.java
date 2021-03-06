package net.springBootAuthentication.springBootAuthentication.controller;

import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.multipart.MultipartFile;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomAdminInterface;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomAdminProfile;
import net.springBootAuthentication.springBootAuthentication.exception.ResourceNotFoundException;
import net.springBootAuthentication.springBootAuthentication.model.AddressModel;
import net.springBootAuthentication.springBootAuthentication.model.AdminProfileModel;
import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;
import net.springBootAuthentication.springBootAuthentication.repository.AddressRepository;
import net.springBootAuthentication.springBootAuthentication.repository.AdminProfileRepository;
import net.springBootAuthentication.springBootAuthentication.repository.RegisterRepository;

@RestController
@RequestMapping("/ltp")
public class AdminProfileController {
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private AdminProfileRepository adminProfileRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RegisterRepository registerRepository;

    @RequestMapping(value = "/createAdminProfile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> postJob(@RequestPart(value = "data") String data,
            @RequestPart(value = "img") final MultipartFile img) throws IOException {

        try {

            AddressModel addressModel = new AddressModel();
            AdminProfileModel adminModel = new AdminProfileModel();
            LocalDate date = LocalDate.now();

            CustomAdminProfile customAdminProfile = objectMapper.readValue(data, CustomAdminProfile.class);
            RegisterModel registerModel = registerRepository.findById(customAdminProfile.getAccountId())
                    .orElseThrow(() -> new ResourceNotFoundException("not found"));

            String tempImageName = img.getOriginalFilename();
            String imageName = tempImageName.replaceAll("\\s+", "_");
            File convertfile = new File("src/main/resources/img/"
                    + String.format("%d%s%s", customAdminProfile.getAccountId(), date, imageName));
            convertfile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertfile);
            fout.write(img.getBytes());
            fout.close();

            addressModel.setCity(customAdminProfile.getCity());
            addressModel.setCountry(customAdminProfile.getCountry());
            addressModel.setPostal(customAdminProfile.getPostalcode());
            addressModel.setStreet(customAdminProfile.getStreet());
            addressModel.setZipcode(customAdminProfile.getZipcode());

            registerModel.setUsername(customAdminProfile.getUsername());
            registerModel.setPassword(new BCryptPasswordEncoder().encode(customAdminProfile.getPassword()));
            registerModel.setEmail(customAdminProfile.getEmail());
            registerRepository.save(registerModel);

            addressRepository.saveAndFlush(addressModel);
            adminModel.setAddressId(addressModel.getId());
            adminModel.setAccountId(customAdminProfile.getAccountId());
            adminModel.setAge(customAdminProfile.getAge());
            adminModel.setBirthdate(customAdminProfile.getBirthdate());
            adminModel.setFirstname(customAdminProfile.getFirstname());
            adminModel.setLastname(customAdminProfile.getLastname());
            adminModel.setGender(customAdminProfile.getGender());
            adminModel.setEmail(customAdminProfile.getEmail());
            adminModel.setPhonenumber(customAdminProfile.getPhonenumber());
            adminModel.setImage("src/main/resources/img/" + String.format("%d%s%s", customAdminProfile.getAccountId(), date, imageName));
            adminProfileRepository.saveAndFlush(adminModel);

            List<Object> lists = new ArrayList<>();

            lists.add(addressModel);
            lists.add(adminModel);
            return ResponseEntity.ok(lists);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/retrieveProfile")
    public ResponseEntity<Object> retrieveProfile(@RequestBody AdminProfileModel aModel)
            throws ResourceNotFoundException {
        List<Object> val = new ArrayList<>();
        Long id = aModel.getAccountId();
        Long existed = adminProfileRepository.checkAdminId(id);
        RegisterModel account = registerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        List<CustomAdminInterface> ProfileId = adminProfileRepository.retrieveProfileAdmin(id);

        if (existed != null) {
            val.add(ProfileId);
            val.add(account);
            val.add(false);
        } else {
            val.add(account);
            val.add(true);

        }
        return ResponseEntity.ok(val);
    }

    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> update(@RequestPart(value = "data") String data,
            @RequestPart(value = "img") final MultipartFile img) throws IOException, ResourceNotFoundException {

        CustomAdminProfile address = objectMapper.readValue(data, CustomAdminProfile.class);
        LocalDate date = LocalDate.now();
        Long id = address.getAddress_id();
        Long adminId = address.getId();
        AddressModel adModel = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        AdminProfileModel apModel = adminProfileRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        RegisterModel registerModel = registerRepository.findById(address.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        String tempImageName = img.getOriginalFilename();
        String imageName = tempImageName.replaceAll("\\s+", "_");
        File convertfile = new File(
                "src/main/resources/img/" + String.format("%d%s%s", address.getAccountId(), date, imageName));
        convertfile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertfile);
        fout.write(img.getBytes());
        fout.close();

        registerModel.setUsername(address.getUsername());
        registerModel.setPassword(new BCryptPasswordEncoder().encode(address.getPassword()));
        registerModel.setEmail(address.getEmail());
        registerRepository.save(registerModel);

        adModel.setCity(address.getCity());
        adModel.setCountry(address.getCountry());
        adModel.setPostal(address.getPostalcode());
        adModel.setStreet(address.getStreet());
        adModel.setZipcode(address.getZipcode());
        addressRepository.save(adModel);

        apModel.setAge(address.getAge());
        apModel.setBirthdate(address.getBirthdate());
        apModel.setEmail(address.getEmail());
        apModel.setFirstname(address.getFirstname());
        apModel.setGender(address.getGender());
        apModel.setLastname(address.getLastname());
        apModel.setPhonenumber(address.getPhonenumber());
        apModel.setImage("src/main/resources/img/" + String.format("%d%s%s", address.getAccountId(), date, imageName));
        adminProfileRepository.save(apModel);
        return ResponseEntity.ok("Profile Updated!");

    }

    @PostMapping(value = "/updateAdmin")
    public ResponseEntity<?> updateAdmin(@RequestBody CustomAdminProfile entity) throws BadRequest {
        try {
            Long id = entity.getAddress_id();
            Long adminId = entity.getId();

            AddressModel adModel = addressRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("not found"));
            AdminProfileModel apModel = adminProfileRepository.findById(adminId)
                    .orElseThrow(() -> new ResourceNotFoundException("not found"));

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


            adModel.setCity(entity.getCity());
            adModel.setCountry(entity.getCountry());
            adModel.setPostal(entity.getPostalcode());
            adModel.setStreet(entity.getStreet());
            adModel.setZipcode(entity.getZipcode());
            addressRepository.save(adModel);

            apModel.setAge(entity.getAge());
            apModel.setBirthdate(dateFormat.format(entity.getBirthdate()));
            apModel.setEmail(entity.getEmail());
            apModel.setFirstname(entity.getFirstname());
            apModel.setGender(entity.getGender());
            apModel.setLastname(entity.getLastname());
            apModel.setPhonenumber(entity.getPhonenumber());
            apModel.setImage(entity.getImg());
            adminProfileRepository.save(apModel);

            return ResponseEntity.ok("updated");
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.FORBIDDEN);
        }

    }

}
