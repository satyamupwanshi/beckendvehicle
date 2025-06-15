package com.motovehicle.vehicledealership.repository;

import com.motovehicle.vehicledealership.controller.VehicleController;
import com.motovehicle.vehicledealership.model.UserEntity;
import com.motovehicle.vehicledealership.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByIsSoldFalse();

    List<Vehicle> findByUser(UserEntity user);
}
