package com.motovehicle.vehicledealership.model;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
    private String email; // optional for login
}

