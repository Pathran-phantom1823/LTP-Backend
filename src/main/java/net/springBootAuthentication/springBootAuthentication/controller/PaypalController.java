package net.springBootAuthentication.springBootAuthentication.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import net.springBootAuthentication.springBootAuthentication.customModel.PaymentRequestModel;
import net.springBootAuthentication.springBootAuthentication.customModel.PaymentSuccessModel;
import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;
import net.springBootAuthentication.springBootAuthentication.model.Response;
import net.springBootAuthentication.springBootAuthentication.repository.PaypalPaymentRepository;
import net.springBootAuthentication.springBootAuthentication.repository.RegisterRepository;
import net.springBootAuthentication.springBootAuthentication.repository.RoleRepository;
import net.springBootAuthentication.springBootAuthentication.services.PaypalService;

@Controller
@RequestMapping("/ltp")
public class PaypalController {

	@Autowired
	PaypalService service;
	
	@Autowired
	PaypalPaymentRepository payment_repository;
	
	@Autowired
	RoleRepository role;
	
	@Autowired
	RegisterRepository paid_account;
	
	@Value("${vue.address}")
	private String address;
	
	@PostMapping("/test")
	public ResponseEntity<String> test(@RequestBody PaymentRequestModel order) {
		return ResponseEntity.ok("testing success");
	}

	@PostMapping("/pay")
	public ResponseEntity<Response> payment(@RequestBody PaymentRequestModel order) {
		try {
			Payment payment = service.createPayment(order.getTotal(), order.getCurrency(), order.getMethod(),
					order.getIntent(), order.getDescription(), address + "user",
					address + "paypal/success/" + order.getPlanID());
			for (Links link : payment.getLinks()) {
				if (link.getRel().equals("approval_url")) {
					ArrayList<HashMap<String, String>> data = new ArrayList<>();
					HashMap<String, String> url = new HashMap<String, String>();
					url.put("url", link.getHref());
					data.add(url);
					return ResponseEntity.ok(new Response(200, "Redirecting", data));
				}
			}

		} catch (PayPalRESTException e) {

			return ResponseEntity.ok(new Response(500, e.getMessage(), new ArrayList<>()));
		}
		return ResponseEntity.ok(new Response(200, "Initializing payment", new ArrayList<>()));
	}

//	@GetMapping("/cancel")
//	public ResponseEntity<String> cancelPay() {
//		return ResponseEntity.ok("cancelled");
//	}

//	@GetMapping("/success/{id}/{paymentId}/{PayerID}")
//	public ResponseEntity<Response> successPay(
//			@PathVariable(value = "id") String id, 
//			@PathVariable(value = "paymentId") String paymentId, 
//			@PathVariable(value = "PayerID") String payerId) {
//		try {
//			Payment payment = service.executePayment(paymentId, payerId);
//			if (payment.getState().equals("approved")) {
////				PaypalPayment paypal = new PaypalPayment(
////						payment.getTransactions().get(0).getDescription(), 
////						Double.parseDouble(payment.getTransactions().get(0).getAmount().getTotal()), 
////						payment.getTransactions().get(0).getAmount().getCurrency(),
////						Integer.parseInt(id),
////						payment.toJSON());
//				payment_repository.pay(
//						Float.parseFloat(payment.getTransactions().get(0).getAmount().getTotal()), 
//						payment.getTransactions().get(0).getAmount().getCurrency(), 
//						payment.getTransactions().get(0).getDescription(),
//						payment.toJSON(),
//						Long.valueOf(Integer.parseInt(id)));
//				ArrayList<String> paidAccounts = new ArrayList<>();
//				Optional<RegisterModel> paid = paid_account.findById(Long.valueOf(Integer.parseInt(id)));
//				
//				String new_role = role.findById(paid.get().getRoleid()).get().getRoleType();
//				paidAccounts.add(new_role);
//				return ResponseEntity.ok(new Response(200, "Initializing payment", paidAccounts));
//			}
//		} catch (PayPalRESTException e) {
//			System.out.println(e.getMessage());
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		return ResponseEntity.ok(new Response(200, "Initializing payment", new ArrayList<>()));
//	}
	
	@PostMapping("/success")
	public ResponseEntity<Response> successPay(@RequestBody PaymentSuccessModel successful_pay){
		String message = "";
		int status = 0;
		ArrayList<String> paidAccounts = new ArrayList<>();
		try {
			Payment payment = service.executePayment(successful_pay.getPaymentID(), successful_pay.getPayerID());
			if (payment.getState().equals("approved")) {
				payment_repository.pay(
						Float.parseFloat(payment.getTransactions().get(0).getAmount().getTotal()), 
						payment.getTransactions().get(0).getAmount().getCurrency(), 
						payment.getTransactions().get(0).getDescription(),
						payment.toJSON(),
						Long.valueOf(Integer.parseInt(successful_pay.getUserID())),
						Long.valueOf(Integer.parseInt(successful_pay.getPlanID())),
						successful_pay.getDateCreated());
				Optional<RegisterModel> paid = paid_account.findById(Long.valueOf(Integer.parseInt(successful_pay.getUserID())));
				System.out.println("====user id===== " + paid.get().getRoleid());
				String new_role = role.findById(paid.get().getRoleid()).get().getRoleType();
				paidAccounts.add(new_role);
				status = 200;
				message = "Paid Succesfully";
			}
		} catch (PayPalRESTException e) {
			System.out.println(e.getMessage());
			message = "PayPayRestException";
			status = 500;
			paidAccounts.add(e.toString());
		} catch (Exception e) {
			message = "Internal Server Error";
			status = 500;
			paidAccounts.add(e.toString());
			System.out.println(e.getMessage());
		}
		return ResponseEntity.ok(new Response(status, message, paidAccounts));
	}
}
