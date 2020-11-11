package net.springBootAuthentication.springBootAuthentication.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.springBootAuthentication.springBootAuthentication.model.AdminProfileModel;
import net.springBootAuthentication.springBootAuthentication.model.AgencyProfileModel;
import net.springBootAuthentication.springBootAuthentication.model.ProfileModel;
import net.springBootAuthentication.springBootAuthentication.repository.AgencyProfileRepository;
import net.springBootAuthentication.springBootAuthentication.repository.ProfileRepository;

@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    private AgencyProfileRepository agencyProfileRepository;

    @Autowired
    private ProfileRepository profileRepository;

    
    @PostMapping(value = "/getAgencyImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getAdminProfiles(@RequestBody AgencyProfileModel entity) throws IOException {
        try {
            Long id = entity.getAccountId();
            // System.out.println(id);
            String file = agencyProfileRepository.getAgencyImage(id);
            if (file == null) {
                return ResponseEntity.ok(null);
            } else {
                ClassPathResource files = new ClassPathResource("img/" + file);
                byte[] bytes = StreamUtils.copyToByteArray(files.getInputStream());

                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
            }
        } catch (Exception e) {
            return ResponseEntity.ok(e);
        }
    }

    @PostMapping(value = "/getProfile", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getProfiles(@RequestBody ProfileModel entity) throws IOException {
        try {
            Long id = entity.getAccountId();
            // System.out.println(id);
            String file = profileRepository.getImage(id);
            if (file == null) {
                return ResponseEntity.ok(null);
            } else {
                ClassPathResource files = new ClassPathResource("img/" + file);
                byte[] bytes = StreamUtils.copyToByteArray(files.getInputStream());

                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
            }
        } catch (Exception e) {
            return ResponseEntity.ok(e);
        }
    }

    @PostMapping(value = "/getAdminProfile", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getAdminProfiles(@RequestBody AdminProfileModel entity) throws IOException {
        try {
            Long id = entity.getAccountId();
            // System.out.println(id);
            String file = profileRepository.getAdminImage(id);
            if (file == null) {
                return ResponseEntity.ok(null);
            } else {
                ClassPathResource files = new ClassPathResource("img/" + file);
                byte[] bytes = StreamUtils.copyToByteArray(files.getInputStream());

                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
            }
        } catch (Exception e) {
            return ResponseEntity.ok(e);
        }
    }
}
