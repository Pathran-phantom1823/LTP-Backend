package net.springBootAuthentication.springBootAuthentication.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomComment;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomForum;
import net.springBootAuthentication.springBootAuthentication.customModel.Register;
import net.springBootAuthentication.springBootAuthentication.exception.ResourceNotFoundException;
import net.springBootAuthentication.springBootAuthentication.model.CommentLikesModel;
import net.springBootAuthentication.springBootAuthentication.model.ForumTransactionsModel;
import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;
import net.springBootAuthentication.springBootAuthentication.model.RoleModel;
import net.springBootAuthentication.springBootAuthentication.repository.CommentLikesRepository;
import net.springBootAuthentication.springBootAuthentication.repository.ForumTransactionsRepository;
import net.springBootAuthentication.springBootAuthentication.repository.RegisterRepository;
import net.springBootAuthentication.springBootAuthentication.repository.RoleRepository;

import javax.persistence.NonUniqueResultException;

@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private ForumTransactionsRepository forumTransactionRepository;

    @Autowired
    private CommentLikesRepository commentsLikesRepository;

    @PostMapping("/register")

    public ResponseEntity<?> addAccount(@RequestBody Register entity) throws BadRequest {
        try {
            RegisterModel account = new RegisterModel();
            Integer roleId = registerRepository.getRoleIdByType(entity.getRoleType());
            System.out.println(entity.getRoleType());
            System.out.println(roleId);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
//            Date expiration = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.MONTH, 1);
            String expiration = dateFormat.format(c.getTime());
            
            account.setUsername(entity.getUsername());
            account.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
            account.setEmail(entity.getEmail());
            account.setIsDisabled("false");
            account.setDateCreated(dateFormat.format(date));
            account.setExpired("false");
            account.setIsMember("false");
            account.setRoleid(roleId);
            account.setExpirationDate(expiration);

            registerRepository.save(account);

            ArrayList<Object> list = new ArrayList<>();

            list.add(account);

            return ResponseEntity.ok(list);
        }catch (NonUniqueResultException e){
            return  ResponseEntity.ok(e);
        }
        catch (Exception e) {
            return ResponseEntity.ok(e);
        }

    }

    @PostMapping("/addRole")
    public ResponseEntity<?> addRole(@RequestBody RoleModel data) {
        RoleModel roles = new RoleModel();
        roles.setRoleType(data.getRoleType());
        roleRepository.save(roles);

        return ResponseEntity.ok(roles);
    }

    @PostMapping(value = "/checUsername")
    public ResponseEntity<?> checkUsernameExist(@RequestBody RegisterModel entity) {
        String username = entity.getUsername();
        String res = registerRepository.checkUsernameExist(username);
        try {
            if (res != null) {
                return ResponseEntity.ok("Username is Unavailable");
            } else {
                return ResponseEntity.ok("Username is Available");
            }
        } catch (Exception e) {
            return ResponseEntity.ok(e);
        }
    }

    @PostMapping(value = "/checkEmail")
    public ResponseEntity<?> checkEmailExist(@RequestBody RegisterModel entity) {
        String email = entity.getEmail();
        String res = registerRepository.checkEmailExist(email);
        try {
            if (res != null) {
                return ResponseEntity.ok("Email is Unavailable");
            } else {
                return ResponseEntity.ok("Email is Available");
            }
        } catch (Exception e) {
            return ResponseEntity.ok(e);
        }
    }

    @GetMapping("/getPost")
    public ResponseEntity<?> getPost() {
        List<CustomForum> forum = forumTransactionRepository.getPost();
        try {
            if (forum == null) {
                return ResponseEntity.ok(null);
            } else {
                return ResponseEntity.ok(forum);
            }
        } catch (Exception e) {
            return ResponseEntity.ok(e);
        }
    }

    @PostMapping(value = "/getForumDetails")
    public ResponseEntity<?> postMethodName(@RequestBody ForumTransactionsModel entity)
            throws ResourceNotFoundException {
        try {
            Long id = entity.getPostId();
            List<CustomForum> details = forumTransactionRepository.getForumDetails(id);
            return ResponseEntity.ok(details);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping(value = "/getComment")
    public ResponseEntity<?> getComment(@RequestBody CustomComment entity) {
        try {
            Long id = entity.getPostId();
            List<CustomForum> list = forumTransactionRepository.getComment(id);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.ok(e);
        }
    }

    @GetMapping(value = "/getLikes")
    public ResponseEntity<?> postMethodName() {
        List<CommentLikesModel> res = commentsLikesRepository.findAll();
        return ResponseEntity.ok(res);
    }
}
