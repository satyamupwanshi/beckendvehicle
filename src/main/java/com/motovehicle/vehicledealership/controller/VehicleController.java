package com.motovehicle.vehicledealership.controller;

import com.motovehicle.vehicledealership.model.Vehicle;
import com.motovehicle.vehicledealership.service.CloudinaryService;
import com.motovehicle.vehicledealership.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // Endpoint to receive Cloudinary image URL from frontend
    @PostMapping("/cloud")
    public ResponseEntity<String> addVehicleFromCloud(
            @RequestBody Map<String, String> request
    ) {
        String title = request.get("title");
        String description = request.get("description");
        double price = Double.parseDouble(request.get("price"));
        String type = request.get("type");
        String imageUrl = request.get("image");

        Vehicle vehicle = Vehicle.builder()
                .title(title)
                .description(description)
                .price(price)
                .image(imageUrl)
                .build();

        vehicleService.saveVehicle(vehicle);
        return ResponseEntity.ok("Vehicle saved with Cloudinary image.");
    }

    @GetMapping
    public List<Vehicle> listAvailableVehicles() {
        return vehicleService.getAllAvailableVehicles();
    }
}
