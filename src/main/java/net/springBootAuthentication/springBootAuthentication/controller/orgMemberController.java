package net.springBootAuthentication.springBootAuthentication.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.aspectj.weaver.MemberKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.springBootAuthentication.springBootAuthentication.customModel.Register;
import net.springBootAuthentication.springBootAuthentication.exception.ResourceNotFoundException;
import net.springBootAuthentication.springBootAuthentication.model.Registration;
import net.springBootAuthentication.springBootAuthentication.model.Role;
import net.springBootAuthentication.springBootAuthentication.model.orgMembers;
import net.springBootAuthentication.springBootAuthentication.repository.RoleRepository;
import net.springBootAuthentication.springBootAuthentication.repository.UserRepository;
import net.springBootAuthentication.springBootAuthentication.repository.orgMemberRepository;
// import net.springBootAuthentication.springBootAuthentication.services.orgMemberService;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/ltp")
public class orgMemberController {

    ObjectMapper objecMapper = new ObjectMapper();

    // @Autowired
    // orgMemberService oms;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    UserRepository userRepository;

    @Autowired
    orgMemberRepository orgMemberRepository;

    @RequestMapping(value = "/member/create", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> addOrganizationMember(@RequestPart(value = "account") String account,
            @RequestPart(value = "org") String org) throws IOException {
        Registration registration = new Registration();
        orgMembers orgMembers = new orgMembers();
        Role role = new Role();
        LocalDate date = LocalDate.now();

        Register acc = objecMapper.readValue(account, Register.class);
        orgMembers OrgMembers = objecMapper.readValue(org, orgMembers.class);

        role.setName("Org-Member");
        roleRepository.save(role);
        roleRepository.flush();
          
        registration.setEmail(acc.getEmail());
        registration.setUsername(acc.getUsername());
        registration.setPassword(new BCryptPasswordEncoder().encode(acc.getPassword()));
        registration.setRoleId(role.getId());
        registration.setExpired("false");
        registration.setisDisabled("false");
        registration.setIsMember("false");
        registration.setCreatedAt(date);
        userRepository.save(registration);
        userRepository.flush();
        orgMembers.setAccountId(registration.getId());
        orgMembers.setOrgId(OrgMembers.getOrgId());
        orgMemberRepository.save(orgMembers);
        return ResponseEntity.ok(registration);
    }

    // @RequestMapping(value = "/getmembers", method = RequestMethod.POST, consumes
    // = MediaType.MULTIPART_FORM_DATA_VALUE)
    // public ResponseEntity<?> members(@RequestPart(value = "id") Integer id)throws
    // IOException{
    // // final List<orgMembers> orgMembers = orgMemberRepository.getMembers(id);
    // return ResponseEntity.ok("success");
    // }

    // @RequestMapping(value = "/getmembers", method = RequestMethod.POST, consumes
    // = MediaType.MULTIPART_FORM_DATA_VALUE)
    // public ResponseEntity<?> getmembers(@RequestPart(value = "id") Registration
    // data)throws IOException{
    // // int temp = Integer.valueOf(id);
    // Registration datas = objecMapper.readValue(data, Registration.class);
    // datas.setId(data.getId());
    // // Registration acc = objecMapper.readValue(id, Registration.class);
    // System.out.println(data);

    // // List<orgMembers> members = oms.getMembers(Integer.parseInt(id));
    // // if(members == null){
    // // return new ArrayList<orgMembers>();
    // // }else{
    // return new ArrayList<orgMembers>();
    // // }
    // }

    @PostMapping(value = "/getmembers")
    public ResponseEntity<?> postMethodName(@RequestBody Register entity) {
        Long id = entity.getId();
        System.out.println(id);
        List<Registration> member = userRepository.getMembers(id);
        System.out.println(member);
        return ResponseEntity.ok(member);
    }

    @PutMapping(value = "/deleteMember")
    public ResponseEntity<?> deleteMember(@RequestBody Register data)throws ResourceNotFoundException{
        Long id = data.getId();
        Registration member = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member not Found"));
        System.out.println(id);

        member.setEmail(data.getEmail());
        member.setUsername(data.getUsername());
        member.setPassword(data.getPassword());
        member.setRoleId(data.getRoleId());
        member.setExpired(data.getExpired());
        member.setisDisabled(data.getisDisabled());
        member.setIsMember(data.getIsMember());
        member.setCreatedAt(data.getCreatedAt().toLocalDate());

        userRepository.save(member);

        return ResponseEntity.ok(member);

    }

    // @PutMapping("/updateMember")
    // public ResponseEntity<?> updateMember(@RequestBody Registration data) {
    //     // System.out.println(data);
    //     orgMemberRepository.updateMembers(data.getId(), data.getUsername(), data.getEmail(), data.getAccountType(),
    //             data.getExpired(), data.getIsMember());

    //     return ResponseEntity.ok("updated");
    // }

    @PutMapping(value="/updateMember")
    public ResponseEntity<?> putMethodName(@RequestBody Registration entity) {
        Long id = entity.getId();
        Registration member = userRepository.getOne(id);
        member.setUsername(entity.getUsername());
        member.setEmail(entity.getEmail());
        member.setPassword(entity.getPassword());
        member.setRoleId(entity.getRoleId());
        member.setExpired(entity.getExpired());

        userRepository.save(member);
        
        return ResponseEntity.ok("updated");
    }

}
