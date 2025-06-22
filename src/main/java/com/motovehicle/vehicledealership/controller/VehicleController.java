package com.motovehicle.vehicledealership.controller;

import com.motovehicle.vehicledealership.model.UserEntity;
import com.motovehicle.vehicledealership.model.Vehicle;
import com.motovehicle.vehicledealership.repository.UserRepository;
import com.motovehicle.vehicledealership.repository.VehicleRepository;
import com.motovehicle.vehicledealership.service.CloudinaryService;
import com.motovehicle.vehicledealership.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication; // ✅ CORRECT
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    // Endpoint to receive Cloudinary image URL from frontend

    @PostMapping("/cloud")
    public ResponseEntity<String> addVehicleFromCloud(
            @RequestBody Map<String, String> request,
            Authentication authentication
    ) {
        String title = request.get("title");
        String description = request.get("description");
        double price = Double.parseDouble(request.get("price"));
        String type = request.get("type");
        String imageUrl = request.get("image");

        String username = authentication.getName(); // ✅ get logged-in user
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Vehicle vehicle = Vehicle.builder()
                .title(title)
                .description(description)
                .price(price)
                .image(imageUrl)
                .user(user)
                .build();

        vehicleService.saveVehicle(vehicle);
        return ResponseEntity.ok("Vehicle saved with Cloudinary image.");
    }


    @GetMapping
    public List<Vehicle> listAvailableVehicles() {
        return vehicleService.getAllAvailableVehicles();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        // Optional: check if the user owns the vehicle
        if (!vehicle.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorized to delete this vehicle");
        }

        vehicleRepository.delete(vehicle);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }


}