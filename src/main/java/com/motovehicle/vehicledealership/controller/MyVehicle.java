package com.motovehicle.vehicledealership.controller;

import com.motovehicle.vehicledealership.model.UserEntity;
import com.motovehicle.vehicledealership.model.Vehicle;
import com.motovehicle.vehicledealership.repository.UserRepository;
import com.motovehicle.vehicledealership.repository.VehicleRepository;
import com.motovehicle.vehicledealership.repository.VehicleRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyVehicle {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository1 vehicleRepository;

    @GetMapping("/my-vehicles")
    public ResponseEntity<?> getUserVehicles(Authentication authentication) {
        String username = authentication.getName(); // ✅ extract username from token

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Vehicle> vehicles = vehicleRepository.findByUser(user);
        return ResponseEntity.ok(vehicles);
    }


}
