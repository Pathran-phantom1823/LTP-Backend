package net.springBootAuthentication.springBootAuthentication.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomAccount;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomAgencyProfile;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomAgencyProfileInterface;
import net.springBootAuthentication.springBootAuthentication.exception.ResourceNotFoundException;
import net.springBootAuthentication.springBootAuthentication.model.AddressModel;
import net.springBootAuthentication.springBootAuthentication.model.AgencyProfileModel;
import net.springBootAuthentication.springBootAuthentication.model.CategoryModel;
import net.springBootAuthentication.springBootAuthentication.model.ProfileSkillsModel;
import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;
import net.springBootAuthentication.springBootAuthentication.model.SkillsModel;
import net.springBootAuthentication.springBootAuthentication.repository.AddressRepository;
import net.springBootAuthentication.springBootAuthentication.repository.AgencyProfileRepository;
import net.springBootAuthentication.springBootAuthentication.repository.CategoryRepository;
import net.springBootAuthentication.springBootAuthentication.repository.ProfileSkillsRepository;
import net.springBootAuthentication.springBootAuthentication.repository.RegisterRepository;
import net.springBootAuthentication.springBootAuthentication.repository.SkillsRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/ltp")
public class AgencyProfileController {

    @Autowired
    private AgencyProfileRepository agencyProfileRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private SkillsRepository skillsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProfileSkillsRepository profileSkillsRepository;

    @Autowired
    private RegisterRepository registerRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/createAgencyProfile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createAgencyProfile(@RequestPart(value = "data") String data,
            @RequestPart(value = "img") final MultipartFile img) throws IOException, ResourceNotFoundException {

        HashMap<String, Object> map = new HashMap<>();
        AgencyProfileModel agencyProfileModel = new AgencyProfileModel();
        AddressModel addressModel = new AddressModel();
        SkillsModel skillsModel = new SkillsModel();
        CategoryModel categoryModel = new CategoryModel();
        ProfileSkillsModel profileSkillsModel = new ProfileSkillsModel();

        LocalDate date = LocalDate.now();

        CustomAgencyProfile entity = objectMapper.readValue(data, CustomAgencyProfile.class);

        RegisterModel registerModel = registerRepository.findById(entity.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        String tempImageName = img.getOriginalFilename();
        String imageName = tempImageName.replaceAll("\\s+", "_");
        File convertfile = new File(
                "src/main/resources/img/" + String.format("%d%s%s", entity.getAccountId(), date, imageName));
        convertfile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertfile);
        fout.write(img.getBytes());
        fout.close();

        registerModel.setUsername(entity.getUsername());
        registerModel.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
        registerModel.setEmail(entity.getEmail());
        registerRepository.save(registerModel);

        addressModel.setCity(entity.getCity());
        addressModel.setCountry(entity.getCountry());
        addressModel.setZipcode(entity.getZipCode());
        addressModel.setPostal(entity.getPostalCode());
        addressModel.setStreet(entity.getStreet());
        addressRepository.saveAndFlush(addressModel);

        skillsModel.setSkillname(entity.getSkillName().toString());
        skillsModel.setTimestamps(date);
        skillsRepository.saveAndFlush(skillsModel);

        categoryModel.setName(entity.getCategory().toString());
        categoryModel.setTimestamps(date);
        categoryRepository.saveAndFlush(categoryModel);

        agencyProfileModel.setAbout(entity.getAbout());
        agencyProfileModel.setEffectDate(entity.getEffectDate());
        agencyProfileModel.setName(entity.getName());
        agencyProfileModel.setImage(String.format("%d%s%s", entity.getAccountId(), date, imageName));
        agencyProfileModel.setPhoneNumber(entity.getPhoneNumber());
        agencyProfileModel.setPricing(entity.getPricing());
        agencyProfileModel.setWebsite(entity.getWebsite());
        agencyProfileModel.setAddressId(addressModel.getId());
        agencyProfileModel.setCategoryId(categoryModel.getId());
        agencyProfileModel.setAccountId(entity.getAccountId());
        agencyProfileModel.setFromDay(entity.getFromDay());
        agencyProfileModel.setFromTime(entity.getFromTime());
        agencyProfileModel.setToDay(entity.getToDay());
        agencyProfileModel.setToTime(entity.getToTime());
        agencyProfileRepository.saveAndFlush(agencyProfileModel);

        profileSkillsModel.setProfileid(agencyProfileModel.getId());
        profileSkillsModel.setSkillid(skillsModel.getId());
        profileSkillsModel.setTimestamps(date);
        profileSkillsRepository.save(profileSkillsModel);

        map.put("address", addressModel);
        map.put("skills", skillsModel);
        map.put("category", categoryModel);
        map.put("profile", agencyProfileModel);
        map.put("profileSkills", profileSkillsModel);

        return ResponseEntity.ok(map);
    }

    @PostMapping(value = "/retrieveProfileAgency")
    public ResponseEntity<?> postMethodName(@RequestBody AgencyProfileModel entity) throws ResourceNotFoundException {
        Long id = entity.getAccountId();
        Long exist = agencyProfileRepository.checkAgencyExisted(id);
        List<Object> res = new ArrayList<>();
        RegisterModel account = registerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        List<CustomAgencyProfileInterface> result = agencyProfileRepository.getAgencyProfile(id);

        if (exist != null) {
            res.add(result);
            res.add(account);
            res.add(false);
        } else {
            res.add(account);
            res.add(true);
        }

        return ResponseEntity.ok(res);
    }

    @PostMapping(value = "/retrieveAccount")
    public ResponseEntity<?> getAccount(@RequestBody AgencyProfileModel entity) throws ResourceNotFoundException {
        Long id = entity.getAccountId();
        RegisterModel account = registerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        return ResponseEntity.ok(account);
    }

    @RequestMapping(value = "/updateAgencyProfilewithImage", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> updateAgencyProfilewithImage(@RequestPart(value = "data") String data,
            @RequestPart(value = "img") final MultipartFile img) throws IOException, ResourceNotFoundException {

        LocalDate date = LocalDate.now();

        CustomAgencyProfile entity = objectMapper.readValue(data, CustomAgencyProfile.class);

        AgencyProfileModel agencyProfileModel = agencyProfileRepository.findById(entity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        AddressModel addressModel = addressRepository.findById(entity.getAddressId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        SkillsModel skillsModel = skillsRepository.findById(entity.getSkillId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        CategoryModel categoryModel = categoryRepository.findById(entity.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        RegisterModel registerModel = registerRepository.findById(entity.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("not Found"));

        String tempImageName = img.getOriginalFilename();
        String imageName = tempImageName.replaceAll("\\s+", "_");
        File convertfile = new File(
                "src/main/resources/img/" + String.format("%d%s%s", entity.getAccountId(), date, imageName));
        convertfile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertfile);
        fout.write(img.getBytes());
        fout.close();

        addressModel.setCity(entity.getCity());
        addressModel.setCountry(entity.getCountry());
        addressModel.setZipcode(entity.getZipCode());
        addressModel.setPostal(entity.getPostalCode());
        addressModel.setStreet(entity.getStreet());
        addressRepository.saveAndFlush(addressModel);

        skillsModel.setSkillname(entity.getSkillName().toString());
        skillsRepository.saveAndFlush(skillsModel);

        categoryModel.setName(entity.getCategory().toString());
        categoryRepository.saveAndFlush(categoryModel);

        registerModel.setUsername(entity.getUsername());
        registerModel.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
        registerModel.setEmail(entity.getEmail());
        registerRepository.save(registerModel);

        agencyProfileModel.setAbout(entity.getAbout());
        agencyProfileModel.setEffectDate(entity.getEffectDate());
        agencyProfileModel.setName(entity.getName());
        agencyProfileModel.setImage(String.format("%d%s%s", entity.getAccountId(), date, imageName));
        agencyProfileModel.setPhoneNumber(entity.getPhoneNumber());
        agencyProfileModel.setPricing(entity.getPricing());
        agencyProfileModel.setWebsite(entity.getWebsite());
        agencyProfileModel.setFromDay(entity.getFromDay());
        agencyProfileModel.setFromTime(entity.getFromTime());
        agencyProfileModel.setToDay(entity.getToDay());
        agencyProfileModel.setToTime(entity.getToTime());
        agencyProfileRepository.saveAndFlush(agencyProfileModel);

        return ResponseEntity.ok("Updated");
    }

    @RequestMapping(value = "/updateAgencyProfile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> updateAgencyProfile(@RequestPart(value = "data") String data)
            throws IOException, ResourceNotFoundException {

        CustomAgencyProfile entity = objectMapper.readValue(data, CustomAgencyProfile.class);

        AgencyProfileModel agencyProfileModel = agencyProfileRepository.findById(entity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        AddressModel addressModel = addressRepository.findById(entity.getAddressId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        SkillsModel skillsModel = skillsRepository.findById(entity.getSkillId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        CategoryModel categoryModel = categoryRepository.findById(entity.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        RegisterModel registerModel = registerRepository.findById(entity.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("not Found"));

        addressModel.setCity(entity.getCity());
        addressModel.setCountry(entity.getCountry());
        addressModel.setZipcode(entity.getZipCode());
        addressModel.setPostal(entity.getPostalCode());
        addressModel.setStreet(entity.getStreet());
        addressRepository.saveAndFlush(addressModel);

        skillsModel.setSkillname(entity.getSkillName().toString());
        skillsRepository.saveAndFlush(skillsModel);

        categoryModel.setName(entity.getCategory().toString());
        categoryRepository.saveAndFlush(categoryModel);

        registerModel.setUsername(entity.getUsername());
        registerModel.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
        registerModel.setEmail(entity.getEmail());
        registerRepository.save(registerModel);

        agencyProfileModel.setAbout(entity.getAbout());
        agencyProfileModel.setEffectDate(entity.getEffectDate());
        agencyProfileModel.setName(entity.getName());
        agencyProfileModel.setImage(entity.getImage());
        agencyProfileModel.setPhoneNumber(entity.getPhoneNumber());
        agencyProfileModel.setPricing(entity.getPricing());
        agencyProfileModel.setWebsite(entity.getWebsite());
        agencyProfileModel.setFromDay(entity.getFromDay());
        agencyProfileModel.setFromTime(entity.getFromTime());
        agencyProfileModel.setToDay(entity.getToDay());
        agencyProfileModel.setToTime(entity.getToTime());
        agencyProfileRepository.saveAndFlush(agencyProfileModel);

        return ResponseEntity.ok("updated");
    }

}
