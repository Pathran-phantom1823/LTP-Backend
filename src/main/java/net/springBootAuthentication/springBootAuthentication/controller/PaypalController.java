package net.springBootAuthentication.springBootAuthentication.controller;

import java.util.ArrayList;
import java.util.HashMap;

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
import net.springBootAuthentication.springBootAuthentication.model.PaypalPayment;
import net.springBootAuthentication.springBootAuthentication.model.Response;
import net.springBootAuthentication.springBootAuthentication.repository.PaypalPaymentRepository;
import net.springBootAuthentication.springBootAuthentication.services.PaypalService;

@Controller
@RequestMapping("/ltp")
public class PaypalController {

	@Autowired
	PaypalService service;
	
	@Autowired
	PaypalPaymentRepository payment_repository;
	
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
					address + "user/paypal/success");
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

			e.printStackTrace();
		}
		return ResponseEntity.ok(new Response(200, "Initializing payment", new ArrayList<>()));
	}

//	@GetMapping("/cancel")
//	public ResponseEntity<String> cancelPay() {
//		return ResponseEntity.ok("cancelled");
//	}

	@GetMapping("/success/{id}/{paymentId}/{PayerID}")
	public ResponseEntity<Response> successPay(
			@PathVariable(value = "id") String id, 
			@PathVariable(value = "paymentId") String paymentId, 
			@PathVariable(value = "PayerID") String payerId) {
		try {
			Payment payment = service.executePayment(paymentId, payerId);
			if (payment.getState().equals("approved")) {
				PaypalPayment paypal = new PaypalPayment(
						payment.getTransactions().get(0).getDescription(), 
						Double.parseDouble(payment.getTransactions().get(0).getAmount().getTotal()), 
						payment.getTransactions().get(0).getAmount().getCurrency(),
						Integer.parseInt(id),
						payment.toJSON());
				payment_repository.save(paypal);
				return ResponseEntity.ok(new Response(200, "Initializing payment", new ArrayList<>()));
				
			}
		} catch (PayPalRESTException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ResponseEntity.ok(new Response(200, "Initializing payment", new ArrayList<>()));
	}

}
