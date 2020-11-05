package net.springBootAuthentication.springBootAuthentication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.hibernate.engine.spi.EntityUniqueKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomJobApplicant;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomJobHistory;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomJobs;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomTransactionJobs;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomUser;
import net.springBootAuthentication.springBootAuthentication.customModel.Register;
import net.springBootAuthentication.springBootAuthentication.exception.ResourceNotFoundException;
import net.springBootAuthentication.springBootAuthentication.model.JobApplicants;
import net.springBootAuthentication.springBootAuthentication.model.JobTransactionModel;
import net.springBootAuthentication.springBootAuthentication.model.Jobs;
import net.springBootAuthentication.springBootAuthentication.model.JobsTransaction;
import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;

import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

import net.springBootAuthentication.springBootAuthentication.model.SaveJob;
import net.springBootAuthentication.springBootAuthentication.repository.JobApplicantsRepository;
import net.springBootAuthentication.springBootAuthentication.repository.JobsRepository;
import net.springBootAuthentication.springBootAuthentication.repository.JobsTransactionRepository;
import net.springBootAuthentication.springBootAuthentication.repository.RegisterRepository;
import net.springBootAuthentication.springBootAuthentication.repository.SaveJobRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/ltp")
public class JobController {

    ObjectMapper objectMapper = new ObjectMapper();

    public String tempFile;

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private JobsRepository jobsRepository;

    @Autowired
    private JobsTransactionRepository jobsTransactionRepository;

    @Autowired
    private JobApplicantsRepository jobApplicantRepository;

    @Autowired
    private SaveJobRepository saveJobRepository;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> postJob(@RequestPart(value = "postDetails") String postDetails,
            @RequestPart(value = "file") final MultipartFile file) throws IOException {

        // System.out.println(postDetails);
        // System.out.println(file);
        try {
            Jobs jobs2 = new Jobs();

            Jobs jobs = objectMapper.readValue(postDetails, Jobs.class);
            LocalDate date = LocalDate.now();
            String tempFileName = file.getOriginalFilename();
            String filename = tempFileName.replaceAll("\\s+", "_");

            File convertfile = new File(
                    "src/main/resources/files/" + String.format("%d%s%s", jobs.getPostById(), date, filename));

            convertfile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertfile);
            fout.write(file.getBytes());
            fout.close();

            // objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            jobs2.setTitle(jobs.getTitle());
            jobs2.setDescription(jobs.getDescription());
            jobs2.setCategory(jobs.getCategory());
            jobs2.setSubject(jobs.getSubject());
            jobs2.setLanguageFrom(jobs.getLanguageFrom().toString());
            jobs2.setLanguageTo(jobs.getLanguageTo().toString());
            jobs2.setFromDate(jobs.getFromDate());
            jobs2.setToDate(jobs.getToDate());
            jobs2.setFromPrice(jobs.getFromPrice());
            jobs2.setToPrice(jobs.getToPrice());
            jobs2.setPriceType(jobs.getPriceType());
            jobs2.setPostById(jobs.getPostById());
            jobs2.setFile(String.format("%d%s%s", jobs.getPostById(), date, filename));
            jobs2.setDatePosted(date);
            jobs2.setVisibility(jobs.getVisibility());
            jobs2.setlevelOfConfidentiality(jobs.getlevelOfConfidentiality());
            jobs2.setFixedPrice(jobs.getFixedPrice());
            jobs2.setType(jobs.getType());
            jobs2.setIsAvailable("true");
            // jobs2.setPostById(user.getId());
            jobsRepository.save(jobs2);

            return ResponseEntity.ok(jobs2);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.FORBIDDEN);
        }

    }

    // @PostMapping("/sample")
    // public ResponseEntity<?>postJob(@RequestBody Jobs jobs, @RequestBody
    // Registration registration){

    // }
    @PostMapping(value = "/getFile")
    public ResponseEntity<?> getFiles(@RequestBody RegisterModel data) throws IOException {
        try {
            Long id = data.getId();
            List<String> res = new ArrayList<>();
            List<File> finaResults = new ArrayList<>();
            // System.out.println(jobsRepository.getFile(id));
            List<Jobs> jobs = jobsRepository.getFile(id);
            jobs.forEach(el -> {
                tempFile = el.getFile();
                res.add(el.getFile());
            });
            for (int i = 0; i < res.size(); i++) {
                File file = ResourceUtils.getFile("classpath:" + res.get(i));
                finaResults.add(file);
            }
            // res.add(file);
            // System.out.println(jobs);
            return ResponseEntity.ok(finaResults);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.FORBIDDEN);
        }

    }

    @GetMapping(value = "/getFiles/{file}")
    public void getMethodName(@PathVariable(value = "file") String file, HttpServletResponse servletResponse) throws FileNotFoundException {
        try {
            // String temp = file.getFile();

            
            // List<String> res = jobsRepository.getFileThroughParameter(temp);
            // System.out.println(res.get(0));
            File files = ResourceUtils.getFile("classpath:" + "files/" + file);
            FileInputStream stream = new FileInputStream(files);
            servletResponse.setContentType("application/pdf;charset=UTF-8");
            servletResponse.setCharacterEncoding("UTF-8");
            servletResponse.setHeader("Content-Disposition","attachment;filename=" + java.net.URLEncoder.encode(file, "UTF-8"));
            byte[] b = new byte[100];
            int len;
            while((len = stream.read(b)) > 0){
                servletResponse.getOutputStream().write(b, 0, len);
            }
            servletResponse.getOutputStream().flush(); 
            servletResponse.getOutputStream().close();
            stream.close();
            // InputStreamResource resource = new InputStreamResource( new FileInputStream(files));
            System.out.println(files);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @PostMapping(value = "/getYourJobs")
    public ResponseEntity<?> getYourJobs(@RequestBody RegisterModel data) throws ResourceNotFoundException {
        try {
            Long id = data.getId();
            List<Jobs> jobs = jobsRepository.getFile(id);
            return new ResponseEntity<>(jobs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping(value = "getAllJobs")
    public ResponseEntity<?> getAllJobs(@RequestBody RegisterModel data)throws BadRequest {
        try {
            Long id = data.getId();
            List<CustomJobs> jobs = jobsRepository.getAllJobs(id);
            return ResponseEntity.ok(jobs);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping(value = "/getJob")
    public ResponseEntity<?> getJobById(@RequestBody Jobs data) throws NumberFormatException {
        // System.out.println("id" + param);
        try {
            Long resId = data.getId();
            // CustomJobs custom;
            // Jobs jobs = jobsRepository.findById(resId).orElseThrow(() -> new ResourceNotFoundException("not Found"));
            List<CustomJobs> job = jobsRepository.getJobById(resId);
            // List<String> res = jobsRepository.getFileThroughParameter(jobs.getFile());
            // // System.out.println(res.get(0));
            // File files = ResourceUtils.getFile("classpath:" + res.get(0));
            // System.out.println(files);
            return ResponseEntity.ok(job);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/editJob")
    public ResponseEntity<?> getJobByDetails(@RequestBody Jobs data) throws NumberFormatException {
        // System.out.println("id" + param);
        try {
            Long resId = data.getId();
            // CustomJobs custom;
            // Jobs jobs = jobsRepository.findById(resId).orElseThrow(() -> new ResourceNotFoundException("not Found"));
            List<CustomJobs> job = jobsRepository.getJobById(resId);
            // List<String> res = jobsRepository.getFileThroughParameter(jobs.getFile());
            // // System.out.println(res.get(0));
            // File files = ResourceUtils.getFile("classpath:" + res.get(0));
            // System.out.println(files);
            return ResponseEntity.ok(job);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="/getJobDetails")
    public ResponseEntity<?> getJobDetails(@RequestBody Jobs entity) {
        try {
            Long resId = entity.getId();
            List<CustomJobs> job = jobsRepository.getJobById(resId);
            return ResponseEntity.ok(job);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NO_CONTENT);
        }
    }
    

    @PostMapping(value = "/apply-job")
    public ResponseEntity<?> postMethodName(@RequestBody JobApplicants data) throws ResourceNotFoundException {

        try {
            JobApplicants applicants = new JobApplicants();
            LocalDate date = LocalDate.now();

            applicants.setApplicantId(data.getApplicantId());
            applicants.setDateApplied(date);
            applicants.setJobId(data.getJobId());
            applicants.setStatus("pending");

            jobApplicantRepository.save(applicants);

            return ResponseEntity.ok(applicants);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/save-job")
    public ResponseEntity<?> saveJob(@RequestBody SaveJob data) throws ResourceNotFoundException {
        try {
            LocalDate date = LocalDate.now();
            Jobs job = jobsRepository.findById(data.getJobId())
                    .orElseThrow(() -> new ResourceNotFoundException("job not found"));
            SaveJob saveJob = new SaveJob();

            job.setIsAvailable("false");
            jobsRepository.save(job);
            saveJob.setDateSaved(date);
            saveJob.setJobId(data.getJobId());
            saveJob.setPostedById(data.getPostedById());
            saveJob.setSavedById(data.getSavedById());

            saveJobRepository.save(saveJob);

            return ResponseEntity.ok("job is saved");
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping(value = "/getsavejob")
    public ResponseEntity<?> postMethodName(@RequestBody SaveJob data) {
        try {
            Long id = data.getSavedById();

            List<CustomJobs> saveJobs = saveJobRepository.getSaveJobs(id);

            return ResponseEntity.ok(saveJobs);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping(value = "/getacceptedjobs")
    public ResponseEntity<?> getAcceptedJobs(@RequestBody SaveJob data) {
        try {
            Long id = data.getSavedById();

            List<CustomJobs> acceptedJobs = jobApplicantRepository.getAcceptedJobs(id);

            return ResponseEntity.ok(acceptedJobs);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping(value = "/getCurrentUser")
    public ResponseEntity<?> getCurrentUser(@RequestBody RegisterModel entity) throws ResourceNotFoundException {
        RegisterModel user = registerRepository.findById(entity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<String> list = new ArrayList<>();
        String username = user.getUsername();
        String email = user.getEmail();

        list.add(username);
        list.add(email);
        return ResponseEntity.ok(list);
    }


    @PostMapping(value="/get-my-jobs")
    public ResponseEntity<?> getMyJobs(@RequestBody Register entity)throws Forbidden{
        try {
            Long id = entity.getId();

            List<CustomJobs> myjobs = jobsRepository.getMyJobs(id);
            for (CustomJobs customJobs : myjobs) {
                System.out.println(customJobs.getToPrice());
            }

            return ResponseEntity.ok(myjobs);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        
    }

    @PostMapping(value = "/getBids")
    public ResponseEntity<?> getBids(@RequestBody Jobs data) throws NumberFormatException {
        // System.out.println("id" + param);
        try {
            Long id = data.getId();
            // CustomJobs custom;
            List<CustomUser> bids = jobsRepository.getBids(id);
            // System.out.println(jobsRepository.getBids(id));
            return ResponseEntity.ok(bids);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value="/accept-bidder")
    public ResponseEntity<?> acceptBidder(@RequestBody JobApplicants entity)throws Forbidden {
        try {
            LocalDate date = LocalDate.now();

            Jobs job = jobsRepository.findById(entity.getJobId())
                    .orElseThrow(() -> new ResourceNotFoundException("job not found"));

            job.setIsAvailable("false");
            jobsRepository.save(job);

            JobApplicants applicants = jobApplicantRepository.findById(entity.getId())
            .orElseThrow(() -> new ResourceNotFoundException("applicant not found"));



            applicants.setStatus(entity.getStatus());
            applicants.setDateAccepted(date);
            jobApplicantRepository.save(applicants);

            // List<JobApplicants> accept = jobApplicantRepository.acceptBidder(jobId, applicantId, status, isAvailable);

            return ResponseEntity.ok("accept");

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }        
    }

    @PostMapping(value="/decline-bidder")
    public ResponseEntity<?> declineBidder(@RequestBody JobApplicants entity)throws Forbidden {
        try {

            Jobs job = jobsRepository.findById(entity.getJobId())
                    .orElseThrow(() -> new ResourceNotFoundException("job not found"));

            job.setIsAvailable("true");
            jobsRepository.save(job);

            JobApplicants applicants = jobApplicantRepository.findById(entity.getId())
            .orElseThrow(() -> new ResourceNotFoundException("applicant not found"));

            applicants.setStatus(entity.getStatus());
            jobApplicantRepository.save(applicants);

            // List<JobApplicants> accept = jobApplicantRepository.acceptBidder(jobId, applicantId, status, isAvailable);

            return ResponseEntity.ok("decline");

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }        
    }

    @PostMapping(value="/assign-job")
    public ResponseEntity<?> assignJob(@RequestBody JobTransactionModel entity)throws ResourceNotFoundException{
        try {
            JobTransactionModel hJobsTransaction = new JobTransactionModel();
            LocalDate date = LocalDate.now();

            hJobsTransaction.setJobId(entity.getJobId());
            hJobsTransaction.setOrgId(entity.getOrgId());
            hJobsTransaction.setWorkedBy(entity.getWorkedBy());
            hJobsTransaction.setPostedBy(entity.getPostedBy());
            hJobsTransaction.setDatePosted(date);

            jobsTransactionRepository.save(hJobsTransaction);

            return ResponseEntity.ok(hJobsTransaction);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }        
        
    }

    @PostMapping(value="/getAssignedJobs")
    public ResponseEntity<?> getAssignedJobs(@RequestBody JobTransactionModel entity)throws ResourceNotFoundException {
        try {
            Long id = entity.getOrgId();
            List<CustomTransactionJobs> list = jobsTransactionRepository.getAssignedJobs(id);

            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NO_CONTENT);
        }        
    }

    
    @PostMapping(value="/getAssignedJobsDetails")
    public ResponseEntity<?> getAssignedJobsDetail(@RequestBody JobTransactionModel entity)throws ResourceNotFoundException {
        try {
            Long id = entity.getId();
            List<CustomTransactionJobs> list = jobsTransactionRepository.getAssignedJobsById(id);

            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NO_CONTENT);
        }        
    }
    
    @PostMapping(value="/getJobHistory")
    public ResponseEntity<?> getJobHistory(@RequestBody JobTransactionModel entity)throws ResourceNotFoundException {
        try {
            Long id = entity.getOrgId();
            List<CustomJobHistory> list = jobsTransactionRepository.getJobHistory(id);

            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NO_CONTENT);
        }        
    }


    @PostMapping(value="/getMyJobHistory")
    public ResponseEntity<?> postMethodName(@RequestBody Register entity) {
        try {
            Long id = entity.getId();
            List<CustomJobApplicant> list = jobApplicantRepository.getMyJobHistory(id);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NO_CONTENT);
        }
        
    }
    
    @PostMapping(value="/deleteJob")
    public ResponseEntity<?> deleteJob(@RequestBody Jobs entity)throws ResourceNotFoundException {
        LocalDate date = LocalDate.now();
        try {
            Long id = entity.getId();
            Jobs job = jobsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
            job.setDeleted(date);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Deleted");
    }



    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> UpdateJob(@RequestPart(value = "job") String job,
            @RequestPart(value = "file") MultipartFile file) throws IOException {

        // System.out.println(postDetails);
        // System.out.println(file);
        try {
            Jobs jobs = objectMapper.readValue(job, Jobs.class);
            LocalDate date = LocalDate.now();
            String tempFileName = file.getOriginalFilename();
            String filename = tempFileName.replaceAll("\\s+", "_");

            File convertfile = new File(
                    "src/main/resources/files/" + String.format("%d%s%s", jobs.getPostById(), date, filename));

            convertfile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertfile);
            fout.write(file.getBytes());
            fout.close();

            Long id = jobs.getId();
            Jobs jobs2 = jobsRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("not found"));
            // objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            jobs2.setTitle(jobs.getTitle());
            jobs2.setDescription(jobs.getDescription());
            jobs2.setCategory(jobs.getCategory());
            jobs2.setSubject(jobs.getSubject());
            jobs2.setLanguageFrom(jobs.getLanguageFrom().toString());
            jobs2.setLanguageTo(jobs.getLanguageTo().toString());
            jobs2.setFromDate(jobs.getFromDate());
            jobs2.setToDate(jobs.getToDate());
            jobs2.setFromPrice(jobs.getFromPrice());
            jobs2.setToPrice(jobs.getToPrice());
            jobs2.setPriceType(jobs.getPriceType());
            jobs2.setPostById(jobs.getPostById());
            jobs2.setFile(String.format("%d%s%s", jobs.getPostById(), date, filename));
            jobs2.setDatePosted(date);
            jobs2.setVisibility(jobs.getVisibility());
            jobs2.setlevelOfConfidentiality(jobs.getlevelOfConfidentiality());
            jobs2.setFixedPrice(jobs.getFixedPrice());
            jobs2.setType(jobs.getType());
            jobs2.setIsAvailable("true");
            // jobs2.setPostById(user.getId());
            jobsRepository.save(jobs2);

            return ResponseEntity.ok(jobs2);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/finish-file", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFinishFile(@RequestPart(value = "job") String job,
    @RequestPart(value = "file") MultipartFile file)throws ResourceNotFoundException, IOException{
        try {
            JobApplicants jobs = objectMapper.readValue(job, JobApplicants.class);
            LocalDate date = LocalDate.now();
            String tempFileName = file.getOriginalFilename();
            String filename = tempFileName.replaceAll("\\s+", "_");

            File convertfile = new File(
                    "src/main/resources/files/" + String.format("%d%s%s", jobs.getApplicantId(), date, filename));

            convertfile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertfile);
            fout.write(file.getBytes());
            fout.close();

            Long id = jobs.getId();
            System.out.println(id);
            JobApplicants jobApplicants = jobApplicantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
            jobApplicants.setFinishedFile(filename);
            jobApplicants.setDateFinished(date);
            jobApplicantRepository.save(jobApplicants);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        
        return ResponseEntity.ok("Uploaded");
    }

    @RequestMapping(value = "/finish-file-agencymember", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> finishInJobTransaction(@RequestPart(value = "job") String job,
    @RequestPart(value = "file") MultipartFile file)throws ResourceNotFoundException, IOException{
        try {
            JobTransactionModel jobs = objectMapper.readValue(job, JobTransactionModel.class);
            LocalDate date = LocalDate.now();
            String tempFileName = file.getOriginalFilename();
            String filename = tempFileName.replaceAll("\\s+", "_");

            File convertfile = new File(
                    "src/main/resources/files/" + String.format("%d%s%s", jobs.getOrgId(), date, filename));

            convertfile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertfile);
            fout.write(file.getBytes());
            fout.close();

            Long id = jobs.getId();
            System.out.println(id);
            JobTransactionModel jobApplicants = jobsTransactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
            jobApplicants.setFinishFile(filename);
            jobApplicants.setDateFinished(date);
            jobsTransactionRepository.save(jobApplicants);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        
        return ResponseEntity.ok("Uploaded");
    

    
    
    
    

}
