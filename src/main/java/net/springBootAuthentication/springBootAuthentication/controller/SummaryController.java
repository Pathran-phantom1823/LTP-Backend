package net.springBootAuthentication.springBootAuthentication.controller;

import java.util.ArrayList;
import java.util.List;

import net.springBootAuthentication.springBootAuthentication.customModel.JobCountInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.springBootAuthentication.springBootAuthentication.customModel.PaymentSummaryModel;
import net.springBootAuthentication.springBootAuthentication.exception.ResourceNotFoundException;
import net.springBootAuthentication.springBootAuthentication.model.Response;
import net.springBootAuthentication.springBootAuthentication.model.UsersSummaryModel;
import net.springBootAuthentication.springBootAuthentication.repository.AdminProfileRepository;

@Controller
@RequestMapping("/ltp")
public class SummaryController {
	
	@Autowired
	AdminProfileRepository summary;
	
	@GetMapping("/user_summary")
	public ResponseEntity<Response> summary() throws ResourceNotFoundException {
		ArrayList<UsersSummaryModel> sum = null;
		try {
			sum = summary.summarize();
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(new Response(500, "invalid_request", new ArrayList<String>()));
		}
		return ResponseEntity.ok(new Response(200, "users_summary", sum));
	}
	
	@GetMapping("/payment_summary")
	public ResponseEntity<Response> paymentSummary() throws ResourceNotFoundException {
		ArrayList<PaymentSummaryModel> sum = null;
		try {
			sum = summary.paymentSummarize();
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(new Response(500, "invalid_request", new ArrayList<String>()));
		}
		return ResponseEntity.ok(new Response(200, "payment_summary", sum));
	}

	@GetMapping("/job-summary")
	public ResponseEntity<?> jobsummary(){
			ArrayList<JobCountInterface> count = null;
			try {
				count = summary.countJobs();
			}catch (Exception e){
				return  ResponseEntity.ok(e);
			}
			return ResponseEntity.ok(count);
	}

}
