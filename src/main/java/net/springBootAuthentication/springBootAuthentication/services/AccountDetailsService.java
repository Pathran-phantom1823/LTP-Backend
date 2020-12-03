package net.springBootAuthentication.springBootAuthentication.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;
import net.springBootAuthentication.springBootAuthentication.model.Response;
import net.springBootAuthentication.springBootAuthentication.repository.LoginRepository;

@Service
public class AccountDetailsService implements UserDetailsService{
	
	@Autowired
	private LoginRepository check;
	
	private AccountDetails accountDetails;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
//		try {
		RegisterModel Account = check.findByUsername(username);
//			accountDetails = new AccountDetails(Account);
////			System.out.println("DB " + Account.getPassword());
//		}catch(Exception e) {
//			System.out.println("Account not Found!");
//		}
		return new User(Account.getUsername(), Account.getPassword(), new ArrayList<>());
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
