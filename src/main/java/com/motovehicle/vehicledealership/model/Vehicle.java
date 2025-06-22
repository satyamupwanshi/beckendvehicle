package com.motovehicle.vehicledealership.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private double price;

    @Column(name = "is_sold")
    private boolean isSold = false;

    private String image;

    @Column(name = "posted_on")
    private LocalDateTime postedOn;

    @PrePersist
    public void setPostedOnTimestamp() {
        this.postedOn = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
