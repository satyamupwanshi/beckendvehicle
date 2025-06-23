package com.motovehicle.vehicledealership.dto;

import lombok.Data;

@Data
public class StartChatRequest {
    private Long sellerId;
    private Long vehicleId;
}

