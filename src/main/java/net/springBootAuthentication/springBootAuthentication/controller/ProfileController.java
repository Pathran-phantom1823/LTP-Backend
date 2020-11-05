package net.springBootAuthentication.springBootAuthentication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomProfiles;
import net.springBootAuthentication.springBootAuthentication.model.AddressModel;
import net.springBootAuthentication.springBootAuthentication.model.CategoryModel;
import net.springBootAuthentication.springBootAuthentication.model.EducationModel;
import net.springBootAuthentication.springBootAuthentication.model.ProfileModel;
import net.springBootAuthentication.springBootAuthentication.model.ProfileSkillsModel;
import net.springBootAuthentication.springBootAuthentication.model.SkillsModel;
import net.springBootAuthentication.springBootAuthentication.repository.AddressRepository;
import net.springBootAuthentication.springBootAuthentication.repository.EducationRepository;
import net.springBootAuthentication.springBootAuthentication.repository.ProfileRepository;
import net.springBootAuthentication.springBootAuthentication.repository.ProfileSkillsRepository;
import net.springBootAuthentication.springBootAuthentication.repository.SkillsRepository;

@RestController
@RequestMapping("/ltp")

public class ProfileController {
    // EntityManager em = emf.createEntityManager();
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private SkillsRepository skillsRepository;

    @Autowired
    private ProfileSkillsRepository profileSkillsRepository;

    @RequestMapping(value = "/createprofile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> postJob(@RequestPart(value = "data") String data,
            @RequestPart(value = "img") final MultipartFile img) throws IOException {

        // System.out.println(postDetails);
        // System.out.println(file);
        try {
            ProfileModel profileModel = new ProfileModel();
            AddressModel addressModel = new AddressModel();
            SkillsModel skillModel = new SkillsModel();
            ProfileSkillsModel profileSkillsModel = new ProfileSkillsModel();
            CategoryModel categoryModel = new CategoryModel();

            CustomProfiles customProfile = objectMapper.readValue(data, CustomProfiles.class);
            // LocalDate date = LocalDate.now();
            LocalDate date = LocalDate.now();
            String tempImageName = img.getOriginalFilename();
            String imageName = tempImageName.replaceAll("\\s+", "_");

            File convertfile = new File(
                    "src/main/resources/img/" + String.format("%d%s%s", customProfile.getAccountId(), date, imageName));

            convertfile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertfile);
            fout.write(img.getBytes());
            fout.close();

            addressModel.setCity(customProfile.getCity());
            addressModel.setCountry(customProfile.getCountry());
            addressModel.setPostal(customProfile.getPostalcode());
            addressModel.setStreet(customProfile.getStreet());
            addressModel.setZipcode(customProfile.getZipcode());
            addressRepository.saveAndFlush(addressModel);

            skillModel.setSkillname(customProfile.getSkillname());
            skillModel.setTimestamps(date);
            skillsRepository.saveAndFlush(skillModel);

            System.out.println(customProfile.getAccountId());
            profileModel.setAccountId(customProfile.getAccountId());
            System.out.println(profileModel.getAccountId());
            profileModel.setImage(String.format("%d%s%s", customProfile.getAccountId(), date, imageName));
            profileModel.setAddressId(addressModel.getId());
            profileModel.setAge(customProfile.getAge());
            profileModel.setBirthdate(customProfile.getBirthdate());
            profileModel.setFirstname(customProfile.getFirstname());
            profileModel.setLastname(customProfile.getLastname());
            profileModel.setPhonenumber(customProfile.getPhonenumber());
            profileModel.setEmail(customProfile.getEmail());
            profileModel.setPricing(customProfile.getPricing());
            profileModel.setGender(customProfile.getGender());
            profileModel.setDateFrom(customProfile.getDateFrom());
            profileModel.setDateTo(customProfile.getDateTo());
            profileModel.setTimeFrom(customProfile.getTimeFrom());
            profileModel.setTimeTo(customProfile.getTimeTo());
            profileRepository.saveAndFlush(profileModel);

            profileSkillsModel.setTimestamps(date);
            profileSkillsModel.setProfileid(profileModel.getId());
            profileSkillsModel.setSkillid(skillModel.getId());
            profileSkillsRepository.saveAndFlush(profileSkillsModel);

            categoryModel.setName(customProfile.getCategory());
            categoryModel.setTimestamps(date);

            List<Object> list = new ArrayList<>();

            list.add(addressModel);
            list.add(skillModel);
            list.add(profileModel);
            list.add(profileSkillsModel);
            list.add(categoryModel);

            return ResponseEntity.ok(list);

        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/create_education_address")
    public ResponseEntity<?> postMethodName(@RequestBody CustomProfiles customProfile) {

        AddressModel addressModel = new AddressModel();
        EducationModel educationModel = new EducationModel();
        LocalDate date = LocalDate.now();
        List<Object> list = new ArrayList<>();

        try {
            addressModel.setCity(customProfile.getCity());
            addressModel.setCountry(customProfile.getCountry());
            addressModel.setPostal(customProfile.getPostalcode());
            addressModel.setStreet(customProfile.getStreet());
            addressModel.setZipcode(customProfile.getZipcode());
            addressRepository.saveAndFlush(addressModel);

            educationModel.setAddressId(addressModel.getId());
            educationModel.setProfileId(customProfile.getId());
            educationModel.setSchoolname(customProfile.getSchoolname());
            educationModel.setSchoolyear(customProfile.getSchoolyear());
            educationModel.setTimestamps(date);
            educationRepository.saveAndFlush(educationModel);

            list.add(addressModel);
            list.add(educationModel);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(list);
    }
}