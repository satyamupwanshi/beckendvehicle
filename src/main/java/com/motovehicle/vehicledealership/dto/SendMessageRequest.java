package com.motovehicle.vehicledealership.dto;

import lombok.Data;

@Data
public class SendMessageRequest {
    private Long chatRoomId;
    private String content;
}

