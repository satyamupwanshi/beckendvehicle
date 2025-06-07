package com.motovehicle.vehicledealership.controller;

import com.motovehicle.vehicledealership.model.Vehicle;
import com.motovehicle.vehicledealership.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<String> addVehicle(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam MultipartFile image
    ) throws IOException {

        String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path imagePath = Paths.get("uploads");
        if (!Files.exists(imagePath)) Files.createDirectories(imagePath);
        Files.copy(image.getInputStream(), imagePath.resolve(filename), StandardCopyOption.REPLACE_EXISTING);

        Vehicle v = Vehicle.builder()
                .title(title)
                .description(description)
                .price(price)
                .image(filename)
                .build();

        vehicleService.saveVehicle(v);
        return ResponseEntity.ok("Vehicle uploaded successfully");
    }

    @GetMapping
    public List<Vehicle> listAvailableVehicles() {
        return vehicleService.getAllAvailableVehicles();
    }
}
