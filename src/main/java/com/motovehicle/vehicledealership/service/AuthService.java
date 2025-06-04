package com.motovehicle.vehicledealership.service;


import com.motovehicle.vehicledealership.model.UserEntity;
import com.motovehicle.vehicledealership.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepo;

    public void register(UserEntity request) {
        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(new BCryptPasswordEncoder().encode(request.getPassword()))
                .build();
        userRepo.save(user);
    }
}

