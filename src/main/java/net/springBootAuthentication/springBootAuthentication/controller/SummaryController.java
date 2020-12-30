package net.springBootAuthentication.springBootAuthentication.controller;

import java.util.ArrayList;
import java.util.List;

import net.springBootAuthentication.springBootAuthentication.customModel.JobCountInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import net.springBootAuthentication.springBootAuthentication.customModel.AgencyChartSummaryRequestModel;
import net.springBootAuthentication.springBootAuthentication.customModel.ChartSummaryRequestModel;
import net.springBootAuthentication.springBootAuthentication.customModel.FinishedJobsYearsSummary;
import net.springBootAuthentication.springBootAuthentication.customModel.IntegerRequestModel;
import net.springBootAuthentication.springBootAuthentication.customModel.PaymentSummaryModel;
import net.springBootAuthentication.springBootAuthentication.customModel.PaymentYearsSummary;
import net.springBootAuthentication.springBootAuthentication.customModel.QuotationChartModel;
import net.springBootAuthentication.springBootAuthentication.customModel.QuotationsYearsSummary;
import net.springBootAuthentication.springBootAuthentication.customModel.ReportsChartModel;
import net.springBootAuthentication.springBootAuthentication.customModel.ReportsYearsSummary;
import net.springBootAuthentication.springBootAuthentication.customModel.SalesChartModel;
import net.springBootAuthentication.springBootAuthentication.customModel.finishedJobModel;
import net.springBootAuthentication.springBootAuthentication.customModel.finishedJobsChartModel;
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
	
	@GetMapping("/sales_years")
	public ResponseEntity<Response> salesYearsSummary() throws ResourceNotFoundException {
		ArrayList<PaymentYearsSummary> sum = null;
		try {
			sum = summary.paymentYearsSummary();
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(new Response(500, "invalid_request", new ArrayList<String>()));
		}
		return ResponseEntity.ok(new Response(200, "sales_years_summary", sum));
	}
	
	@GetMapping("/reports_years")
	public ResponseEntity<Response> reportsYearsSummary() throws ResourceNotFoundException {
		ArrayList<ReportsYearsSummary> sum = null;
		try {
			sum = summary.reportsYearsSummary();
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(new Response(500, "invalid_request", new ArrayList<String>()));
		}
		return ResponseEntity.ok(new Response(200, "reports_years_summary", sum));
	}
	
	@GetMapping("/quotations_years")
	public ResponseEntity<Response> quotationsYearsSummary() throws ResourceNotFoundException {
		ArrayList<QuotationsYearsSummary> sum = null;
		try {
			sum = summary.quotationsYearsSummary();
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(new Response(500, "invalid_request", new ArrayList<String>()));
		}
		return ResponseEntity.ok(new Response(200, "reports_years_summary", sum));
	}
	
	@GetMapping("/finishedJobs_years")
	public ResponseEntity<Response> finishedJobsYearsSummary() throws ResourceNotFoundException {
		ArrayList<FinishedJobsYearsSummary> sum = null;
		try {
			sum = summary.finishedJobsYearsSummary();
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(new Response(500, "invalid_request", new ArrayList<String>()));
		}
		return ResponseEntity.ok(new Response(200, "finishedJobs_years_summary", sum));
	}
	
	@PostMapping("/sales_chart")
	public ResponseEntity<Response> salesChartSummary(@RequestBody ChartSummaryRequestModel request) {
		ArrayList<SalesChartModel> sum = null;
		try {
			sum = summary.salesChartSummary(request.getMonth(), request.getYear());
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(new Response(500, "invalid_request", new ArrayList<String>()));
		}
		return ResponseEntity.ok(new Response(200, "sales_years_summary", sum));
	}
	
	@PostMapping("/finishedJobs_chart")
	public ResponseEntity<Response> finishedJobsChartSummary(@RequestBody ChartSummaryRequestModel request) {
		ArrayList<finishedJobsChartModel> sum = null;
		try {
			sum = summary.finishedJobsChartSummary(request.getMonth(), request.getYear());
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(new Response(500, "invalid_request", new ArrayList<String>()));
		}
		return ResponseEntity.ok(new Response(200, "finishedJobs_summary", sum));
	}
	
	@PostMapping("/reports_chart")
	public ResponseEntity<Response> reportsChartSummary(@RequestBody ChartSummaryRequestModel request) {
		ArrayList<ReportsChartModel> sum = null;
		try {
			sum = summary.reportsChartSummary(request.getMonth(), request.getYear());
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(new Response(500, "invalid_request", new ArrayList<String>()));
		}
		return ResponseEntity.ok(new Response(200, "reports_years_summary", sum));
	}
	
	@PostMapping("/quotations_chart")
	public ResponseEntity<Response> quotationsChartSummary(@RequestBody ChartSummaryRequestModel request) {
		ArrayList<QuotationChartModel> sum = null;
		try {
			sum = summary.quotationChartSummary(request.getMonth(), request.getYear());
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(new Response(500, "invalid_request", new ArrayList<String>()));
		}
		return ResponseEntity.ok(new Response(200, "reports_years_summary", sum));
	}
	
	@GetMapping("/finishedJobs_summary")
	public ResponseEntity<Response> finishedJobsSummary() {
		ArrayList<finishedJobModel> sum = null;
		try {
			sum = summary.finishedJobsSummary();
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(new Response(500, "invalid_request", new ArrayList<String>()));
		}
		return ResponseEntity.ok(new Response(200, "finishedJobs_summary", sum));
	}
	
	@GetMapping("/quotationsTable_summary")
	public ResponseEntity<Response> quotationsTableSummary() {
		ArrayList<finishedJobModel> sum = null;
		try {
			sum = summary.quotationsSummaryTable();
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(new Response(500, "invalid_request", new ArrayList<String>()));
		}
		return ResponseEntity.ok(new Response(200, "finishedJobs_summary", sum));
	}
	
	@GetMapping("/finishedQuotations_size")
	public ResponseEntity<Response> finishedQuotation() {
		ArrayList<Integer> sum = null;
		try {
			sum = summary.finishedQuotationsSize();
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(new Response(500, e.getMessage(), new ArrayList<String>()));
		}
		return ResponseEntity.ok(new Response(200, "finishedQuotation_size", sum));
	}
	
	@GetMapping("/finishedJob_size")
	public ResponseEntity<Response> finishedJobSize() {
		ArrayList<Integer> sum = null;
		try {
			sum = summary.finishedJobSize();
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(new Response(500, e.getMessage(), new ArrayList<String>()));
		}
		return ResponseEntity.ok(new Response(200, "finishedJobs_size", sum));
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
	
	@PostMapping("/agency_job_count")
	public ResponseEntity<?> agencyJobCount(@RequestBody IntegerRequestModel request){
		ArrayList<Integer> total = null;
		try {
			total = summary.agencyCountJobs(request.getNum());
		}catch(Exception e) {
			return ResponseEntity.ok(new Response(500, e.getMessage(), new ArrayList<String>()));
		}
		return ResponseEntity.ok(new Response(200, "agencyJobsTotalCount", total));
	}
	
	@PostMapping("/agency_members_count")
	public ResponseEntity<?> agencyMembersCount(@RequestBody IntegerRequestModel request){
		ArrayList<Integer> total = null;
		try {
			total = summary.agencyMembersTotal(request.getNum());
		}catch(Exception e) {
			return ResponseEntity.ok(new Response(500, e.getMessage(), new ArrayList<String>()));
		}
		return ResponseEntity.ok(new Response(200, "agencyMembersTotal", total));
	}
	
	@PostMapping("/agencyFinishedJobsTableSummary")
	public ResponseEntity<Response> agencyTableJobsSummary(@RequestBody IntegerRequestModel request) {
		ArrayList<finishedJobModel> sum = null;
		try {
			sum = summary.agencyJobsSummary(request.getNum());
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(new Response(500, "invalid_request", new ArrayList<String>()));
		}
		return ResponseEntity.ok(new Response(200, "agencyFinishedJobsTableSummary", sum));
	}
	
	@PostMapping("/agency_finished_jobs")
	public ResponseEntity<Response> agencyFinishedJobsChart(@RequestBody AgencyChartSummaryRequestModel request) {
		ArrayList<finishedJobsChartModel> sum = null;
		try {
			sum = summary.agencyFinishedJobs(request.getMonth(), request.getYear(), request.getId());
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(new Response(500, e.getMessage(), new ArrayList<String>()));
		}
		return ResponseEntity.ok(new Response(200, "agency_finishedJobs_summary", sum));
	}
	
	@PostMapping("/agency_Income")
	public ResponseEntity<Response> agencyIncome(@RequestBody AgencyChartSummaryRequestModel request) {
		ArrayList<finishedJobsChartModel> sum = null;
		try {
			sum = summary.agencyIncome(request.getMonth(), request.getYear(), request.getId());
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(new Response(500, e.getMessage(), new ArrayList<String>()));
		}
		return ResponseEntity.ok(new Response(200, "agency_income_for_chart", sum));
	}
	
	
	@PostMapping("/agency_job_income")
	public ResponseEntity<?> agencyJobIncome(@RequestBody IntegerRequestModel request){
		ArrayList<Double> total = null;
		try {
			total = summary.agencyJobsIncome(request.getNum());
		}catch(Exception e) {
			return ResponseEntity.ok(new Response(500, e.getMessage(), new ArrayList<String>()));
		}
		return ResponseEntity.ok(new Response(200, "agencyJobIncome", total));
	}
		
	@PostMapping("/agency_quotations_income")
	public ResponseEntity<?> agencyQuotationsIncome(@RequestBody IntegerRequestModel request){
		ArrayList<Double> total = null;
		try {
			total = summary.agencyQuotationsIncome(request.getNum());
		}catch(Exception e) {
			return ResponseEntity.ok(new Response(500, e.getMessage(), new ArrayList<String>()));
		}
		return ResponseEntity.ok(new Response(200, "agencyQuotationIncome", total));
	}
}
