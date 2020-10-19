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

import net.bytebuddy.asm.Advice.Local;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomOrgMember;
import net.springBootAuthentication.springBootAuthentication.customModel.Register;
import net.springBootAuthentication.springBootAuthentication.exception.ResourceNotFoundException;
import net.springBootAuthentication.springBootAuthentication.model.OrgMembers;
import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;
import net.springBootAuthentication.springBootAuthentication.model.RoleModel;
import net.springBootAuthentication.springBootAuthentication.repository.RegisterRepository;
import net.springBootAuthentication.springBootAuthentication.repository.RoleRepository;
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
    RegisterRepository registerRepository;

    @Autowired
    orgMemberRepository orgMemberRepository;

    // @RequestMapping(value = "/member/create", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // public ResponseEntity<Object> addOrganizationMember(@RequestPart(value = "account") String account,
    //         @RequestPart(value = "org") String org) throws IOException {
    //     RegisterModel registration = new RegisterModel();
    //     OrgMembers orgMembers = new OrgMembers();
    //     RoleModel role = new RoleModel();
    //     LocalDate date = LocalDate.now();

    //     Register acc = objecMapper.readValue(account, Register.class);
    //     OrgMembers OrgMember = objecMapper.readValue(org, OrgMembers.class);

    //     role.setRoleType("Org-Member");
    //     roleRepository.save(role);
    //     roleRepository.flush();
          
    //     registration.setEmail(acc.getEmail());
    //     registration.setUsername(acc.getUsername());
    //     registration.setPassword(new BCryptPasswordEncoder().encode(acc.getPassword()));
    //     registration.setRoleid(role.getId());
    //     registration.setExpired("false");
    //     registration.setIsDisabled("false");
    //     registration.setIsMember("false");
    //     registration.setDateCreated(date);
    //     registerRepository.save(registration);
    //     registerRepository.flush();
    //     orgMembers.setAccountId(registration.getId());
    //     orgMembers.setOrgId(OrgMember.getOrgId());
    //     orgMemberRepository.save(orgMembers);
    //     return ResponseEntity.ok(registration);
    // }

    @PostMapping(value="/member/create")
    public ResponseEntity<?> createMembers(@RequestBody CustomOrgMember entity) {
        RegisterModel registerModel = new RegisterModel();
        RoleModel roleModel = new RoleModel();
        OrgMembers orgMembers = new OrgMembers();
        LocalDate date = LocalDate.now();

        // roleModel.setRoleType("Org-Member");
        // roleRepository.save(roleModel);
        // roleRepository.flush();

        registerModel.setEmail(entity.getEmail());
        registerModel.setUsername(entity.getUsername());
        registerModel.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
        registerModel.setRoleid(roleModel.getId());
        registerModel.setExpired("false");
        registerModel.setIsDisabled("false");
        registerModel.setIsMember("false");
        registerModel.setDateCreated(date);
        registerModel.setRoleid(entity.getRoleId());
        registerRepository.save(registerModel);
        registerRepository.flush();

        orgMembers.setCreateAt(date);
        orgMembers.setAccountId(registerModel.getId());
        orgMembers.setOrgId(entity.getOrgId());
        orgMemberRepository.save(orgMembers);


        return ResponseEntity.ok("");
    }
    

    @PostMapping(value = "/getmembers")
    public ResponseEntity<?> postMethodName(@RequestBody Register entity) {
        Long id = entity.getId();
        System.out.println(id);
        List<RegisterModel>member = registerRepository.getMembers(id);
        System.out.println(member);
        return ResponseEntity.ok(member);
    }

    @PutMapping(value = "/deleteMember")
    public ResponseEntity<?> deleteMember(@RequestBody Register data)throws ResourceNotFoundException{
        Long id = data.getId();
        RegisterModel member = registerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member not Found"));
        System.out.println(id);

        member.setEmail(data.getEmail());
        member.setUsername(data.getUsername());
        member.setPassword(data.getPassword());
        member.setRoleid(data.getRoleId());
        member.setExpired(data.getExpired());
        member.setIsDisabled(data.getIsDisabled());
        member.setIsMember(data.getIsMember());
        member.setDateCreated(data.getDateCreated());

        registerRepository.save(member);

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
    public ResponseEntity<?> putMethodName(@RequestBody RegisterModel entity) {
        Long id = entity.getId();
        RegisterModel member = registerRepository.getOne(id);
        member.setUsername(entity.getUsername());
        member.setEmail(entity.getEmail());
        member.setPassword(entity.getPassword());
        member.setRoleid(entity.getRoleid());
        member.setExpired(entity.getExpired());

        registerRepository.save(member);
        
        return ResponseEntity.ok("updated");
    }

}
