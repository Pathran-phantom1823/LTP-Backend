package net.springBootAuthentication.springBootAuthentication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.hibernate.engine.spi.EntityUniqueKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomJobApplicant;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomJobHistory;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomJobs;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomQuotationAssigned;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomTransactionJobs;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomUser;
import net.springBootAuthentication.springBootAuthentication.customModel.Register;
import net.springBootAuthentication.springBootAuthentication.exception.ResourceNotFoundException;
import net.springBootAuthentication.springBootAuthentication.model.AdminProfileModel;
import net.springBootAuthentication.springBootAuthentication.model.JobApplicants;
import net.springBootAuthentication.springBootAuthentication.model.JobTransactionModel;
import net.springBootAuthentication.springBootAuthentication.model.Jobs;
import net.springBootAuthentication.springBootAuthentication.model.JobsTransaction;
import net.springBootAuthentication.springBootAuthentication.model.ProfileModel;
import net.springBootAuthentication.springBootAuthentication.model.QuotationAssigmentModel;
import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;

import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;
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
import net.springBootAuthentication.springBootAuthentication.repository.ProfileRepository;
import net.springBootAuthentication.springBootAuthentication.repository.QuotationAssigmentRepository;
import net.springBootAuthentication.springBootAuthentication.repository.RegisterRepository;
import net.springBootAuthentication.springBootAuthentication.repository.SaveJobRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
    private QuotationAssigmentRepository quotationAssigmentRepository;

    @Autowired
    private SaveJobRepository saveJobRepository;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> postJob(@RequestPart(value = "postDetails") String postDetails,
            @RequestPart(value = "file") final MultipartFile file) throws IOException {
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
            List<Jobs> jobs = jobsRepository.getFile(id);
            jobs.forEach(el -> {
                tempFile = el.getFile();
                res.add(el.getFile());
            });
            for (int i = 0; i < res.size(); i++) {
                File file = ResourceUtils.getFile("classpath:" + res.get(i));
                finaResults.add(file);
            }
            return ResponseEntity.ok(finaResults);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.FORBIDDEN);
        }

    }

    @GetMapping(value = "/getFiles/{file}")
    public void getMethodName(@PathVariable(value = "file") String file, HttpServletResponse servletResponse)
            throws FileNotFoundException {
        try {
            File files = ResourceUtils.getFile("classpath:" + "files/" + file);
            FileInputStream stream = new FileInputStream(files);
            servletResponse.setContentType("application/pdf;charset=UTF-8");
            servletResponse.setCharacterEncoding("UTF-8");
            servletResponse.setHeader("Content-Disposition",
                    "attachment;filename=" + java.net.URLEncoder.encode(file, "UTF-8"));
            byte[] b = new byte[100];
            int len;
            while ((len = stream.read(b)) > 0) {
                servletResponse.getOutputStream().write(b, 0, len);
            }
            servletResponse.getOutputStream().flush();
            servletResponse.getOutputStream().close();
            stream.close();
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
    public ResponseEntity<?> getAllJobs(@RequestBody RegisterModel data) throws BadRequest {
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
        try {
            Long resId = data.getId();
            List<CustomJobs> job = jobsRepository.getJobById(resId);
            return ResponseEntity.ok(job);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/editJob")
    public ResponseEntity<?> getJobByDetails(@RequestBody Jobs data) throws NumberFormatException {
        try {
            Long resId = data.getId();
            List<CustomJobs> job = jobsRepository.getJobById(resId);
            return ResponseEntity.ok(job);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/getJobDetails")
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
            if (acceptedJobs == null) {
                return ResponseEntity.ok(null);
            } else {
                return ResponseEntity.ok(acceptedJobs);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping(value = "/getCurrentUser")
    public ResponseEntity<?> getCurrentUser(@RequestBody RegisterModel entity) throws ResourceNotFoundException {
        Long id = entity.getId();
        CustomUser user = registerRepository.getCurrentUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/get-my-jobs")
    public ResponseEntity<?> getMyJobs(@RequestBody Register entity) throws Forbidden {
        try {
            Long id = entity.getId();

            List<CustomJobs> myjobs = jobsRepository.getMyJobs(id);

            return ResponseEntity.ok(myjobs);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/getBids")
    public ResponseEntity<?> getBids(@RequestBody Jobs data) throws NumberFormatException {
        try {
            Long id = data.getId();
            List<CustomUser> bids = jobsRepository.getBids(id);
            return ResponseEntity.ok(bids);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/accept-bidder")
    public ResponseEntity<?> acceptBidder(@RequestBody JobApplicants entity) throws Forbidden {
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

            // List<JobApplicants> accept = jobApplicantRepository.acceptBidder(jobId,
            // applicantId, status, isAvailable);

            return ResponseEntity.ok("accept");

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/decline-bidder")
    public ResponseEntity<?> declineBidder(@RequestBody JobApplicants entity) throws Forbidden {
        try {

            Jobs job = jobsRepository.findById(entity.getJobId())
                    .orElseThrow(() -> new ResourceNotFoundException("job not found"));

            job.setIsAvailable("true");
            jobsRepository.save(job);

            JobApplicants applicants = jobApplicantRepository.findById(entity.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("applicant not found"));

            applicants.setStatus(entity.getStatus());
            jobApplicantRepository.save(applicants);

            // List<JobApplicants> accept = jobApplicantRepository.acceptBidder(jobId,
            // applicantId, status, isAvailable);

            return ResponseEntity.ok("decline");

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/assign-job")
    public ResponseEntity<?> assignJob(@RequestBody JobTransactionModel entity) throws ResourceNotFoundException {
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

    @PostMapping(value = "/getAssignedJobs")
    public ResponseEntity<?> getAssignedJobs(@RequestBody JobTransactionModel entity) throws ResourceNotFoundException {
        try {
            Long id = entity.getOrgId();
            List<CustomTransactionJobs> list = jobsTransactionRepository.getAssignedJobs(id);

            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/getAssignedJobsDetails")
    public ResponseEntity<?> getAssignedJobsDetail(@RequestBody JobTransactionModel entity)
            throws ResourceNotFoundException {
        try {
            Long id = entity.getId();
            List<CustomTransactionJobs> list = jobsTransactionRepository.getAssignedJobsById(id);

            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/getJobHistory")
    public ResponseEntity<?> getJobHistory(@RequestBody JobTransactionModel entity) throws ResourceNotFoundException {
        try {
            Long id = entity.getOrgId();
            List<CustomJobHistory> list = jobsTransactionRepository.getJobHistory(id);

            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/getMyJobHistory")
    public ResponseEntity<?> postMethodName(@RequestBody Register entity) {
        try {
            Long id = entity.getId();
            List<CustomJobApplicant> list = jobApplicantRepository.getMyJobHistory(id);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping(value = "/deleteJob")
    public ResponseEntity<?> deleteJob(@RequestBody Jobs entity) throws ResourceNotFoundException {
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
            Jobs jobs2 = jobsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
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
            @RequestPart(value = "file") MultipartFile file) throws ResourceNotFoundException, IOException {
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
            JobApplicants jobApplicants = jobApplicantRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("not found"));
            jobApplicants.setFinishedFile(filename);
            jobApplicants.setDateFinished(date);
            jobApplicantRepository.save(jobApplicants);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("Uploaded");
    }

    @RequestMapping(value = "/finish-file-orgmember", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFinishFileOrgMember(@RequestPart(value = "job") String job,
            @RequestPart(value = "file") MultipartFile file) throws ResourceNotFoundException, IOException {
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
            JobTransactionModel jobApplicants = jobsTransactionRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("not found"));
            jobApplicants.setFinishFile(filename);
            jobApplicants.setDateFinished(date);
            jobsTransactionRepository.save(jobApplicants);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("Uploaded");
    }

    @RequestMapping(value = "/finish-assigned-quote", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFinshedAssignedQuote(@RequestPart(value = "job") String job,
            @RequestPart(value = "file") MultipartFile file) throws ResourceNotFoundException, IOException {
        try {
            QuotationAssigmentModel jobs = objectMapper.readValue(job, QuotationAssigmentModel.class);
            LocalDate date = LocalDate.now();
            String tempFileName = file.getOriginalFilename();
            String filename = tempFileName.replaceAll("\\s+", "_");

            File convertfile = new File(
                    "src/main/resources/files/" + String.format("%d%s%s", jobs.getAccountId(), date, filename));

            convertfile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertfile);
            fout.write(file.getBytes());
            fout.close();

            Long id = jobs.getJobId();
            Long pid = quotationAssigmentRepository.getQuotationPrimaryId(id);
            QuotationAssigmentModel jobApplicants = quotationAssigmentRepository.findById(pid)
                    .orElseThrow(() -> new ResourceNotFoundException("not found"));
            jobApplicants.setFinishedFile(filename);
            jobApplicants.setDateFinished(date);
            quotationAssigmentRepository.save(jobApplicants);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("Uploaded");
    }

    @RequestMapping(value = "/finish-file-agencymember", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> finishInJobTransaction(@RequestPart(value = "job") String job,
            @RequestPart(value = "file") MultipartFile file) throws ResourceNotFoundException, IOException {
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
            JobTransactionModel jobApplicants = jobsTransactionRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("not found"));
            jobApplicants.setFinishFile(filename);
            jobApplicants.setDateFinished(date);
            jobsTransactionRepository.save(jobApplicants);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("Uploaded");
    }

    @GetMapping(value = "/getQuote")
    public ResponseEntity<?> getQuote() {
        List<CustomJobs> list = jobsRepository.getQuotation();

        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/getQuotation")
    public ResponseEntity<?> getQuotation() {
        List<CustomQuotationAssigned> list = jobsRepository.getQuotationAdmin();

        return ResponseEntity.ok(list);

    }

    @PostMapping(value="/searchJobs")
    public ResponseEntity<?> postMethodName(@RequestBody Jobs entity) {
        try {
            Long id = entity.getPostById();
            String category = entity.getSubject();
            String languageFrom = entity.getLanguageFrom();
            String languageTo = entity.getLanguageTo();
            List<CustomJobs> list = jobsRepository.searchJobs(category, languageFrom, languageTo, id);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.ok(e);
        }
    }

    @PostMapping(value="/searchJobTitle")
    public ResponseEntity<?> search(@RequestBody Jobs entity) {
        try {
            Long id = entity.getPostById();
            String title = entity.getTitle();
            List<CustomJobs> list = jobsRepository.searchJobTitle(title, id);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.ok(e);
        }
    }
    

}
