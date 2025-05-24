package com.motovehicle.vehicledealership.service;

import com.motovehicle.vehicledealership.model.Vehicle;
import com.motovehicle.vehicledealership.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllAvailableVehicles() {
        return vehicleRepository.findByIsSoldFalse();
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
}
