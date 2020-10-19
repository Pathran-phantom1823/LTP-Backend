package net.springBootAuthentication.springBootAuthentication.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;

public class AccountDetails implements UserDetails{
	
	private static final long serialVersionUID = -7628578506103328683L;
	private RegisterModel Account;
	
	public AccountDetails(RegisterModel account) {
		Account = account;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return  new ArrayList<>();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return Account.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return Account.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		boolean expired = false;
		if(Account.getExpired().equals("true")) {
			expired = true;
		}
		return expired;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
