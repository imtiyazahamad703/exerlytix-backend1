package com.immutech.ExerLytix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.immutech.ExerLytix.entity.Member;
import com.immutech.ExerLytix.repo.MemberRepository;

@Service
public class RegistrationService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Member register(Member member) {
        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        try {
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            return memberRepository.save(member);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Email already registered");
        }
    }
}
