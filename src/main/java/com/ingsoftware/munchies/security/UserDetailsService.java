//package com.ingsoftware.munchies.security;
//
//import com.ingsoftware.munchies.repository.AdminRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.security.core.userdetails.User;
//
//import java.util.Collections;
//
//@Service
//@RequiredArgsConstructor
//public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
//
//
//    private final AdminRepository adminRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Admin admin = korisnikRepository.findByIme(email);
//
//        if (admin != null) {
//            return new User(
//                    admin.getEmail(),
//                    admin.getPassword(),
//                    Collections.singleton(new SimpleGrantedAuthority(admin.getEmail()))
//
//            );
//        }
//        throw new UsernameNotFoundException("User not found!");
//    }
//}
