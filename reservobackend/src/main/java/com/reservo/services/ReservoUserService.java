package com.reservo.services;

import com.reservo.Models.ReservoUser;
import com.reservo.Repository.ReservoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ReservoUserService {
    @Autowired
    private ReservoUserRepository reservoUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(String firstName, String lastName, String email, String phone, String username, String password) {
        ReservoUser user = new ReservoUser();
        user.setFirst_name(firstName);
        user.setLast_name(lastName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        reservoUserRepository.save(user);
    }

}
