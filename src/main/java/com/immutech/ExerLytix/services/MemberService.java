package com.immutech.ExerLytix.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.immutech.ExerLytix.entity.Member;
import com.immutech.ExerLytix.repo.MemberRepository;

@Service
public class MemberService implements UserDetailsService {
	
	@Autowired
	private MemberRepository rep;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member m=rep.findByEmail(email);
		
		return new User(m.getEmail(),m.getPassword(),Collections.emptyList());
	}

}
