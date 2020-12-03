package net.springBootAuthentication.springBootAuthentication.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import net.springBootAuthentication.springBootAuthentication.exception.ResourceNotFoundException;
import net.springBootAuthentication.springBootAuthentication.model.MembershipPlanModel;
import net.springBootAuthentication.springBootAuthentication.model.Response;
import net.springBootAuthentication.springBootAuthentication.repository.MembershipPlanRepository;

@Controller
@RequestMapping("/ltp")
public class MembershipPlanController {

	@Autowired
	MembershipPlanRepository plan_repository;

	@PostMapping("/save_plan")
	public ResponseEntity<Response> test(@RequestBody MembershipPlanModel plan) {
		MembershipPlanModel save;
		ArrayList<MembershipPlanModel> _return = new ArrayList<>(); 
		String message = "";
		int status = 0;
		try {
			save = plan_repository.save(plan);
			_return.add(save);
			message = "Inserted Successfully";
			status = 200;
		} catch (Exception e) {
			save = null;
			message = "server_error";
			status = 500;
			System.out.println("================================F A I L D================================" + e
					+ "================================P R I N T================================");
		}
		return ResponseEntity.ok(new Response(status, message , _return));
	}
	
	@GetMapping("/retrieve_plan/")
	public ResponseEntity<Response> retrieve() {
		ArrayList<MembershipPlanModel> _return = new ArrayList<>(); 
		String message = "";
		int status = 0;
		try {
			_return =  new ArrayList<MembershipPlanModel>(plan_repository.findAll());
			message = "Retrieved Succesfully";
			status = 200;
		}catch(Exception e) {
			_return = null;
			message = "server_error";
			status = 500;
			System.out.println("================================F A I L D================================" + e
					+ "================================P R I N T================================");
		}
		return ResponseEntity.ok(new Response(status, message , _return));
	}
	
	@PutMapping("/edit_plan/{id}")
 	public ResponseEntity<Response> updateProduct(
 			@PathVariable(value = "id") long planID,
 			@RequestBody MembershipPlanModel updatedPlan
 	){
		ArrayList<MembershipPlanModel> _return = new ArrayList<>();
		String message = "";
		int status = 0;
		try {
			MembershipPlanModel planInfo = plan_repository.findById(planID).orElseThrow(() -> new ResourceNotFoundException("Plan Not Found"));
			planInfo.setPlan(updatedPlan.getPlan());
			planInfo.setDescription(updatedPlan.getDescription());
			planInfo.setPrice(updatedPlan.getPrice());
			planInfo.setCurrency(updatedPlan.getCurrency());
			plan_repository.save(planInfo);
			
			_return.add(planInfo);
			message = "Updated Successfully";
			status = 200;
		}catch(ResourceNotFoundException e) {
			message = "server_error";
			status = 500;
			System.out.println("================================F A I L D================================" + e
					+ "================================P R I N T================================");
		}catch(Exception e) {
			_return = null;
			message = "server_error";
			status = 500;
			System.out.println("================================F A I L D================================" + e
					+ "================================P R I N T================================");
		}
		
		return ResponseEntity.ok(new Response(status, message , _return));
 	}
	
	@DeleteMapping("/delete_plan/{id}")
 	public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") long planID) {
		String message = "";
		int status = 0;
		try {
	 		MembershipPlanModel planInfo = plan_repository.findById(planID).orElseThrow(() -> new ResourceNotFoundException("Plan Not Found"));
	 		plan_repository.deleteById(planInfo.getId());
	 		message = "Deleted Successfully!";
	 		status = 200;
		}catch(ResourceNotFoundException e) {
			message = "server_error";
			status = 500;
			System.out.println("================================F A I L D================================" + e
					+ "================================P R I N T================================");
		}catch(Exception e) {
			message = "server_error";
			status = 500;
			System.out.println("================================F A I L D================================" + e
					+ "================================P R I N T================================");
		}
		return ResponseEntity.ok(new Response(status, message , new ArrayList<>()));
 	}
}
