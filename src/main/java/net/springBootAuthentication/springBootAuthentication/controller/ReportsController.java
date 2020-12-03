package net.springBootAuthentication.springBootAuthentication.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomReports;
import net.springBootAuthentication.springBootAuthentication.exception.ResourceNotFoundException;
import net.springBootAuthentication.springBootAuthentication.model.ReportsModel;
import net.springBootAuthentication.springBootAuthentication.repository.ReportsRepository;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/ltp")
public class ReportsController {

    @Autowired
    private ReportsRepository reportsRepository;
    
    @PostMapping("/report")
    public ResponseEntity<?> post(@RequestBody ReportsModel entity) {
        ReportsModel rModel = new ReportsModel();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        rModel.setAccountId(entity.getAccountId());
        rModel.setTopic(entity.getTopic());
        rModel.setDescription(entity.getDescription());
        rModel.setReportTimestamp(dateFormat.format(date));
        rModel.setDateResolve(entity.getDateResolve());
        rModel.setResolve(entity.getResolve());

        reportsRepository.save(rModel);
        return ResponseEntity.ok("");

    }
    @PostMapping("/resolve")
    public ResponseEntity<?> postMethodName(@RequestBody ReportsModel entity)throws ResourceNotFoundException {
        
        Long id = entity.getId();
        ReportsModel rmodel = reportsRepository.findById(id).orElseThrow(
        ()-> new ResourceNotFoundException("cannot resolve"));
        rmodel.setResolve(entity.getResolve());
        reportsRepository.save(rmodel);
        return ResponseEntity.ok(" Successfully resolved!");       
    }
    @GetMapping("/getReports")
    public ResponseEntity<?> getMethodName() {
        List<CustomReports> list = reportsRepository.getReports();
        return ResponseEntity.ok(list);
    }
    
    

}
