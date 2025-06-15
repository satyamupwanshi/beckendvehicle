package com.motovehicle.vehicledealership.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/stats")
    public ResponseEntity<?> getAdminStats() {
        // Return some mock or real stats
        return ResponseEntity.ok(Map.of("totalUsers", 100, "totalVehicles", 50));
    }

    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        // delete logic
        return ResponseEntity.ok("Vehicle deleted");
    }
}

