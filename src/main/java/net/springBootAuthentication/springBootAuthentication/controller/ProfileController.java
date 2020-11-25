package net.springBootAuthentication.springBootAuthentication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomProfile;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomProfileInterface;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomProfiles;
import net.springBootAuthentication.springBootAuthentication.customModel.Register;
import net.springBootAuthentication.springBootAuthentication.exception.ResourceNotFoundException;
import net.springBootAuthentication.springBootAuthentication.model.AddressModel;
import net.springBootAuthentication.springBootAuthentication.model.CategoryModel;
import net.springBootAuthentication.springBootAuthentication.model.EducationModel;
import net.springBootAuthentication.springBootAuthentication.model.ProfileModel;
import net.springBootAuthentication.springBootAuthentication.model.ProfileSkillsModel;
import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;
import net.springBootAuthentication.springBootAuthentication.model.SkillsModel;
import net.springBootAuthentication.springBootAuthentication.repository.AddressRepository;
import net.springBootAuthentication.springBootAuthentication.repository.CategoryRepository;
import net.springBootAuthentication.springBootAuthentication.repository.EducationRepository;
import net.springBootAuthentication.springBootAuthentication.repository.ProfileRepository;
import net.springBootAuthentication.springBootAuthentication.repository.ProfileSkillsRepository;
import net.springBootAuthentication.springBootAuthentication.repository.RegisterRepository;
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

        @Autowired
        private CategoryRepository categoryRepository;

        @Autowired
        private RegisterRepository registerRepository;

        @PostMapping(value = "/retrieveProfileDetails")
        public ResponseEntity<?> postMethodName(@RequestBody ProfileModel entity) throws ResourceNotFoundException {
                Long id = entity.getAccountId();
                Long exist = profileRepository.checkAccountExisted(id);
                List<Object> res = new ArrayList<>();
                RegisterModel account = registerRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("not found"));
                List<CustomProfileInterface> retrieveProfileResult = profileRepository.getProfileById(id);
                if (exist != null) {
                        res.add(account);
                        res.add(retrieveProfileResult);
                        res.add(false);
                } else {
                        res.add(account);
                        res.add(true);
                }

                return ResponseEntity.ok(res);
        }

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

                        RegisterModel registerModel = registerRepository.findById(customProfile.getAccountId())
                                        .orElseThrow(() -> new ResourceNotFoundException("not found"));

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                        Date date = new Date();

                        String tempImageName = img.getOriginalFilename();
                        String imageName = tempImageName.replaceAll("\\s+", "_");

                        File convertfile = new File("src/main/resources/img/"
                                        + String.format("%d%s%s", customProfile.getAccountId(), date, imageName));

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

                        skillModel.setSkillname(customProfile.getSkillname().toString());
                        skillModel.setTimestamps(date.toString());
                        skillsRepository.saveAndFlush(skillModel);

                        registerModel.setUsername(customProfile.getUsername());
                        registerModel.setPassword(new BCryptPasswordEncoder().encode(customProfile.getPassword()));
                        registerModel.setEmail(customProfile.getEmail());
                        registerRepository.save(registerModel);

                        System.out.println(customProfile.getAccountId());
                        profileModel.setAccountId(customProfile.getAccountId());
                        System.out.println(profileModel.getAccountId());
                        profileModel.setImage(String.format("%d%s%s", customProfile.getAccountId(), date, imageName));
                        profileModel.setAddressId(addressModel.getId());
                        profileModel.setAge(customProfile.getAge());
                        profileModel.setBirthdate(dateFormat.format(customProfile.getBirthdate()));
                        profileModel.setFirstname(customProfile.getFirstname());
                        profileModel.setLastname(customProfile.getLastname());
                        profileModel.setPhonenumber(customProfile.getPhonenumber());
                        profileModel.setPricing(customProfile.getPricing());
                        profileModel.setGender(customProfile.getGender());
                        profileModel.setDateFrom(customProfile.getDateFrom());
                        profileModel.setDateTo(customProfile.getDateTo());
                        profileModel.setTimeFrom(customProfile.getTimeFrom());
                        profileModel.setTimeTo(customProfile.getTimeTo());
                        profileRepository.saveAndFlush(profileModel);

                        profileSkillsModel.setTimestamps(date.toString());
                        profileSkillsModel.setProfileid(profileModel.getId());
                        profileSkillsModel.setSkillid(skillModel.getId());
                        profileSkillsRepository.saveAndFlush(profileSkillsModel);

                        categoryModel.setName(customProfile.getCategory());
                        categoryModel.setTimestamps(date.toString());

                        List<Object> list = new ArrayList<>();

                        list.add(addressModel);
                        list.add(skillModel);
                        list.add(profileModel);
                        list.add(profileSkillsModel);
                        list.add(categoryModel);

                        System.out.println("Data:" + list);
                        return ResponseEntity.ok(list);

                } catch (Exception e) {
                        // TODO: handle exception
                        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
                }
        }

        @RequestMapping(value = "/updateProfileDetails", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<Object> updateProfile(@RequestPart(value = "data") String data)
                        throws IOException, ResourceNotFoundException {

                CustomProfile entity = objectMapper.readValue(data, CustomProfile.class);

                ProfileModel profileModel = profileRepository.findById(entity.getId())
                                .orElseThrow(() -> new ResourceNotFoundException("notfound"));

                AddressModel addressModel = addressRepository.findById(entity.getAddressId())
                                .orElseThrow(() -> new ResourceNotFoundException("notfound"));
                ;

                SkillsModel skillsModel = skillsRepository.findById(entity.getSkillId())
                                .orElseThrow(() -> new ResourceNotFoundException("notfound"));

                CategoryModel categoryModel = categoryRepository.findById(entity.getCategoryId())
                                .orElseThrow(() -> new ResourceNotFoundException("notfound"));

                RegisterModel registerModel = registerRepository.findById(entity.getAccountId())
                                .orElseThrow(() -> new ResourceNotFoundException("notfound"));

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

                registerModel.setUsername(entity.getUsername());
                registerModel.setPassword(entity.getPassword());
                registerRepository.saveAndFlush(registerModel);

                addressModel.setCity(entity.getCity());
                addressModel.setCountry(entity.getCountry());
                addressModel.setZipcode(entity.getZipcode());
                addressModel.setPostal(entity.getPostalcode());
                addressModel.setStreet(entity.getStreet());
                addressRepository.saveAndFlush(addressModel);

                skillsModel.setSkillname(entity.getSkillname().toString());
                skillsRepository.saveAndFlush(skillsModel);

                categoryModel.setName(entity.getCategory().toString());
                categoryRepository.saveAndFlush(categoryModel);

                profileModel.setAccountId(entity.getAccountId());
                profileModel.setImage(entity.getImage());
                profileModel.setAddressId(addressModel.getId());
                profileModel.setAge(entity.getAge());
                profileModel.setBirthdate(dateFormat.format(entity.getBirthdate()));
                profileModel.setFirstname(entity.getFirstname());
                profileModel.setLastname(entity.getLastname());
                profileModel.setPhonenumber(entity.getPhonenumber());
                profileModel.setPricing(entity.getPricing());
                profileModel.setGender(entity.getGender());
                profileModel.setDateFrom(entity.getDateFrom());
                profileModel.setDateTo(entity.getDateTo());
                profileModel.setTimeFrom(entity.getTimeFrom());
                profileModel.setTimeTo(entity.getTimeTo());
                profileRepository.saveAndFlush(profileModel);

                return ResponseEntity.ok("updated");
        }

        @RequestMapping(value = "/updateProfileDetailswithImage", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<Object> updateAgencyProfilewithImage(@RequestPart(value = "data") String data,
                        @RequestPart(value = "img") final MultipartFile img)
                        throws IOException, ResourceNotFoundException {

                LocalDate date = LocalDate.now();

                CustomProfile entity = objectMapper.readValue(data, CustomProfile.class);
                System.out.println(">>>>Data: " + entity);

                ProfileModel profileModel = profileRepository.findById(entity.getId())
                                .orElseThrow(() -> new ResourceNotFoundException("notfound"));

                AddressModel addressModel = addressRepository.findById(entity.getAddressId())
                                .orElseThrow(() -> new ResourceNotFoundException("notfound"));
                ;

                SkillsModel skillsModel = skillsRepository.findById(entity.getSkillId())
                                .orElseThrow(() -> new ResourceNotFoundException("notfound"));

                CategoryModel categoryModel = categoryRepository.findById(entity.getCategoryId())
                                .orElseThrow(() -> new ResourceNotFoundException("notfound"));

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

                String tempImageName = img.getOriginalFilename();
                String imageName = tempImageName.replaceAll("\\s+", "_");
                File convertfile = new File("src/main/resources/img/"
                                + String.format("%d%s%s", entity.getAccountId(), date, imageName));
                convertfile.createNewFile();
                FileOutputStream fout = new FileOutputStream(convertfile);
                fout.write(img.getBytes());
                fout.close();

                RegisterModel registerModel = registerRepository.findById(entity.getAccountId())
                                .orElseThrow(() -> new ResourceNotFoundException("notfound"));

                registerModel.setUsername(entity.getUsername());
                registerModel.setPassword(entity.getPassword());

                addressModel.setCity(entity.getCity());
                addressModel.setCountry(entity.getCountry());
                addressModel.setZipcode(entity.getZipcode());
                addressModel.setPostal(entity.getPostalcode());
                addressModel.setStreet(entity.getStreet());
                addressRepository.saveAndFlush(addressModel);

                skillsModel.setSkillname(entity.getSkillname().toString());
                skillsRepository.saveAndFlush(skillsModel);

                categoryModel.setName(entity.getCategory().toString());
                categoryRepository.saveAndFlush(categoryModel);

                profileModel.setAccountId(entity.getAccountId());
                profileModel.setImage(imageName);
                profileModel.setAddressId(addressModel.getId());
                profileModel.setAge(entity.getAge());
                profileModel.setBirthdate(dateFormat.format(entity.getBirthdate()));
                profileModel.setFirstname(entity.getFirstname());
                profileModel.setLastname(entity.getLastname());
                profileModel.setPhonenumber(entity.getPhonenumber());
                profileModel.setPricing(entity.getPricing());
                profileModel.setGender(entity.getGender());
                profileModel.setDateFrom(entity.getDateFrom());
                profileModel.setDateTo(entity.getDateTo());
                profileModel.setTimeFrom(entity.getTimeFrom());
                profileModel.setTimeTo(entity.getTimeTo());
                profileRepository.saveAndFlush(profileModel);

                return ResponseEntity.ok("Updated");
        }

        // Update Education Details
        @PostMapping(value = "/updateEducationDetails")
        public ResponseEntity<?> postEducationDetails(@RequestBody CustomProfile entity) {

                AddressModel addressModel = new AddressModel();
                EducationModel educationModel = new EducationModel();

                addressModel.setCity(entity.getCity());
                addressModel.setCountry(entity.getCountry());
                addressModel.setPostal(entity.getPostalcode());
                addressModel.setStreet(entity.getStreet());
                addressModel.setZipcode(entity.getZipcode());
                addressRepository.saveAndFlush(addressModel);

                educationModel.setSchoolname(entity.getSchoolname());
                educationModel.setSchoolyear(entity.getSchoolyear());
                educationModel.setTimestamps(entity.getTimeStamp());
                educationRepository.saveAndFlush(educationModel);

                return ResponseEntity.ok("Updated");
        }

        // Create Education with Address
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