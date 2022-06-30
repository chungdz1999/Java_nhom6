package com.nhom06.webnuocuong.model;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nhom06.webnuocuong.repository.UserRepository;

@Service
@Transactional
public class CustomerServices {
	
	private PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
 
    @Autowired
    private UserRepository customerRepo;
     
 
	public void updateResetPasswordToken(String token, String email) /* throws CustomerNotFoundException */ {
        User customer = customerRepo.findByEmail(email);
        if (customer != null) {
            customer.setResetPasswordToken(token);
            customerRepo.save(customer);
        } else {
            //throw new CustomerNotFoundException("Could not find any customer with the email " + email);
        }
    }
     
    public User getByResetPasswordToken(String token) {
        return customerRepo.findByResetPasswordToken(token);
    }
     
    public void updatePassword(User customer, String newPassword) {
    	
    	//encoder.encode(newPassword  );
       // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
       // String encodedPassword = passwordEncoder.encode(newPassword);
        customer.setPassword(    encoder.encode(newPassword  )    );
         
        customer.setResetPasswordToken(null);
        customerRepo.save(customer);
    }
}