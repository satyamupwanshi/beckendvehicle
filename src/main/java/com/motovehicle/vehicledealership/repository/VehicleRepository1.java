package com.motovehicle.vehicledealership.repository;

import com.motovehicle.vehicledealership.model.UserEntity;
import com.motovehicle.vehicledealership.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository1 extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByUser(UserEntity user); // ✅ This will return vehicles for a given user
}

