package com.motovehicle.vehicledealership.repository;

import com.motovehicle.vehicledealership.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByBuyerAndSellerAndVehicle(UserEntity buyer, UserEntity seller, Vehicle vehicle);
    List<ChatRoom> findByBuyerOrSeller(UserEntity buyer, UserEntity seller);
}
