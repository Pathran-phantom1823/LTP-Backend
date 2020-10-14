package net.springBootAuthentication.springBootAuthentication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.springBootAuthentication.springBootAuthentication.exception.ResourceNotFoundException;
import net.springBootAuthentication.springBootAuthentication.model.AuthenticationRequest;
import net.springBootAuthentication.springBootAuthentication.model.AuthenticationResponse;
import net.springBootAuthentication.springBootAuthentication.model.Product;
import net.springBootAuthentication.springBootAuthentication.repository.ProductsRepository;
import net.springBootAuthentication.springBootAuthentication.services.AccountDetailsService;
import net.springBootAuthentication.springBootAuthentication.utility.JwtUtil;

@RestController
@RequestMapping("/ltp")
//@CrossOrigin(origins = "http://localhost:8080/")
public class ProductController {
	
	@Autowired
	private ProductsRepository productrepository;
	
//	@Autowired
//	private AuthenticationManager authenticationmanager;
//	
//	@Autowired
//	private AccountDetailsService userDetailsService;
//	
//	@Autowired
//	private JwtUtil jwtTokenUtil;

	@GetMapping("/")
	public List<Product> index() {
		return productrepository.findAll();
	}
	
//	@PostMapping("/authenticate")
//	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
//		try {
//			authenticationmanager.authenticate(
//					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//				);
//		}catch (BadCredentialsException e ) {
//			throw new Exception("Incorrect username or password");
//		}
//		
//		final UserDetails userDetails  = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//		final String jwt = jwtTokenUtil.generateToken(userDetails);
//		
//		return ResponseEntity.ok(new AuthenticationResponse(jwt));
//	}
//	
	@PostMapping("/product/add")
	public Product addProduct(@RequestBody Product product){
		return productrepository.save(product);
	}
	
	@PutMapping("/product/update/{id}")
	public Product updateProduct(
			@PathVariable(value = "id") long productId,
			@RequestBody Product updatedProduct
	) throws ResourceNotFoundException{
		Product productInfo = productrepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
		productInfo.setName(updatedProduct.getName());
		productInfo.setStack(updatedProduct.getStack());
		productInfo.setPrice(updatedProduct.getPrice());
		productrepository.save(productInfo);
		
		return productInfo;
	}
	
	@DeleteMapping("/product/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") long productId) throws ResourceNotFoundException {
		Product productInfo = productrepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
		productrepository.deleteById(productInfo.getId());
		return ResponseEntity.ok().build();
	}
}
