package net.springBootAuthentication.springBootAuthentication.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.relation.Role;
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.aspectj.weaver.MemberKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import net.bytebuddy.asm.Advice.Local;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomJobApplicant;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomJobs;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomOrgMember;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomQuotationAssignment;
import net.springBootAuthentication.springBootAuthentication.customModel.Register;
import net.springBootAuthentication.springBootAuthentication.exception.ResourceNotFoundException;
import net.springBootAuthentication.springBootAuthentication.model.OrgMembers;
import net.springBootAuthentication.springBootAuthentication.model.QuotationAssigmentModel;
import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;
import net.springBootAuthentication.springBootAuthentication.model.RoleModel;
import net.springBootAuthentication.springBootAuthentication.repository.JobApplicantsRepository;
import net.springBootAuthentication.springBootAuthentication.repository.QuotationAssigmentRepository;
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
    JobApplicantsRepository jobapplicationRepository;

    @Autowired
    QuotationAssigmentRepository quotationAssigmentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    orgMemberRepository orgMemberRepository;

    @PostMapping(value = "/member/create")
    public ResponseEntity<?> createMembers(@RequestBody CustomOrgMember entity) throws BadRequest {
        try {
            RegisterModel registerModel = new RegisterModel();
            // RoleModel roleModel = new RoleModel();
            // System.out.println(entity.getRoleType());
            Integer roleModel = registerRepository.getRoleIdByType(entity.getRoleType());
            OrgMembers orgMembers = new OrgMembers();
            LocalDate date = LocalDate.now();

            registerModel.setEmail(entity.getEmail());
            registerModel.setUsername(entity.getUsername());
            registerModel.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
            registerModel.setRoleid(roleModel);
            registerModel.setExpired("false");
            registerModel.setIsDisabled("false");
            registerModel.setIsMember("false");
            registerModel.setDateCreated(date);
            registerRepository.saveAndFlush(registerModel);

            orgMembers.setCreateAt(date);
            orgMembers.setAccountId(registerModel.getId());
            orgMembers.setOrgId(entity.getOrgId());
            orgMemberRepository.save(orgMembers);

            return ResponseEntity.ok("");
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping(value = "/getmembers")
    public ResponseEntity<?> postMethodName(@RequestBody Register entity) throws ResourceNotFoundException {
        try {
            Long id = entity.getId();
            System.out.println(id);
            List<RegisterModel> member = registerRepository.getMembers(id);
            System.out.println(member);
            return ResponseEntity.ok(member);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.FORBIDDEN);
        }

    }

    @PutMapping(value = "/deleteMember")
    public ResponseEntity<?> deleteMember(@RequestBody Register data) throws ResourceNotFoundException {
        try {
            Long id = data.getId();
            RegisterModel member = registerRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Member not Found"));
            System.out.println(id);
            member.setEmail(data.getEmail());
            member.setUsername(data.getUsername());
            member.setPassword(data.getPassword());
            member.setRoleid(data.getRoleId());
            member.setExpired(data.getExpired());
            member.setIsDisabled(data.getIsDisabled());
            member.setIsMember(data.getIsMember());
            member.setDateCreated(data.getDateCreated());

            // registerRepository.save(member);

            return ResponseEntity.ok(member);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.FORBIDDEN);
        }

    }

    @PutMapping(value = "/updateMember")
    public ResponseEntity<?> putMethodName(@RequestBody RegisterModel entity) throws BadRequest {
        try {
            Long id = entity.getId();
            RegisterModel member = registerRepository.getOne(id);
            member.setUsername(entity.getUsername());
            member.setEmail(entity.getEmail());
            member.setPassword(entity.getPassword());
            member.setRoleid(entity.getRoleid());
            member.setExpired(entity.getExpired());

            registerRepository.save(member);

            return ResponseEntity.ok("updated");
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping(value = "/getMyAssignedJobs")
    public ResponseEntity<?> getMyAssignedJobs(@RequestBody Register entity) {
        try {
            Long id = entity.getId();
            List<CustomJobs> list = orgMemberRepository.getMyAssignedJobs(id);
            for (CustomJobs customJobs : list) {
                System.out.println(customJobs.getToPrice());
            }

            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/getAccounts")
    public ResponseEntity<?> getAccounts(@RequestBody RoleModel entity) {
        String roleType = entity.getRoleType();
        List<CustomQuotationAssignment> list = jobapplicationRepository.getMembersAccount(roleType);

        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "/assignQuote")
    public ResponseEntity<?> assignQuote(@RequestBody QuotationAssigmentModel entity) {
        QuotationAssigmentModel model = new QuotationAssigmentModel();
        LocalDate date = LocalDate.now();
        try {
            model.setAccountId(entity.getAccountId());
            model.setJobId(entity.getJobId());
            model.setDateAssigned(date);
            model.setStatus(entity.getStatus());
            model.setAssignedById(entity.getAssignedById());

            quotationAssigmentRepository.save(model);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("ok");
    }

    

}
