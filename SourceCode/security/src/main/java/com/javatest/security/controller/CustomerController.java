package com.javatest.security.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javatest.security.config.JwtProvider;
import com.javatest.security.config.JwtResponse;
import com.javatest.security.config.LoginForm;
import com.javatest.security.entity.Customer;
import com.javatest.security.exception.ResourceNotFound;
import com.javatest.security.repository.CustomerRepository;
import com.javatest.security.repository.RoleRepository;
import com.javatest.security.repository.UserRepository;



@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CustomerController {

    @Autowired
    AuthenticationManager authenticationManager;
 
    @Autowired
    UserRepository userRepository;
 
    @Autowired
    RoleRepository roleRepository;
 
    @Autowired
    PasswordEncoder encoder;
 
    @Autowired
    JwtProvider jwtProvider;
    
	@Autowired
	private CustomerRepository cusRepo;

	@GetMapping("/login")
	public String getPwd() {
		return encoder.encode("123456789");
	}

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
 
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
 
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
    
	@GetMapping("/cus")
	public List<Customer> getCus() {
		return cusRepo.findAll();
	}

	@GetMapping("/cussearch")
	public List<Customer> getCusByOpt(@RequestParam("cusname") String cusname,
			@RequestParam("custel") String custel) {
		return cusRepo.getCusByOption(cusname, custel);
	} 
	
	@GetMapping("/cus/{id}")
	public ResponseEntity<Customer> getCusById(@PathVariable(name = "id") Long cusid) throws ResourceNotFound {
		Customer c = cusRepo.findById(cusid)
				.orElseThrow(() -> new ResourceNotFound("Error, Customer not found id => " + cusid));
		return ResponseEntity.ok().body(c);
	}

	@PostMapping("/cus")
	public Customer addCus(@Valid @RequestBody Customer cus) {
		return cusRepo.save(cus);
	}

	@PutMapping("/cus/{id}")
	public ResponseEntity<Customer> updateCus(@PathVariable(name = "id") Long cusid, @Valid @RequestBody Customer cus)
			throws ResourceNotFound {
		Customer customer = cusRepo.findById(cusid)
				.orElseThrow(() -> new ResourceNotFound("Error update, Customer not found id => " + cusid));
		customer.setCusname(cus.getCusname());
		customer.setCustel(cus.getCustel());
		cusRepo.save(customer);
		return ResponseEntity.ok().body(customer);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/cus/{id}")
	public Map<String, Boolean> deleteCus(@PathVariable(name = "id") Long cusid) throws ResourceNotFound {
		Customer c = cusRepo.findById(cusid)
				.orElseThrow(() -> new ResourceNotFound("Error delete, Customer not found id => " + cusid));
		cusRepo.delete(c);
		Map<String, Boolean> res = new HashMap<>();
		res.put("DONE< Deleted Customer id => " + cusid, Boolean.TRUE);
		return res;
	}
}
