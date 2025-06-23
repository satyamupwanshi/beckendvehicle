package com.motovehicle.vehicledealership.controller;

import com.motovehicle.vehicledealership.dto.SendMessageRequest;
import com.motovehicle.vehicledealership.dto.StartChatRequest;
import com.motovehicle.vehicledealership.model.*;
import com.motovehicle.vehicledealership.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatRoomRepository chatRoomRepo;
    private final MessageRepository messageRepo;
    private final UserRepository userRepo;
    private final VehicleRepository vehicleRepo;

    @PostMapping("/start")
    public ResponseEntity<Long> startChat(@RequestBody StartChatRequest req, Authentication auth) {
        String username = auth.getName();
        UserEntity buyer = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserEntity seller = userRepo.findById(req.getSellerId())
                .orElseThrow(() -> new UsernameNotFoundException("Seller not found"));

        Vehicle vehicle = vehicleRepo.findById(req.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        Optional<ChatRoom> existing = chatRoomRepo.findByBuyerAndSellerAndVehicle(buyer, seller, vehicle);
        ChatRoom chatRoom = existing.orElseGet(() -> {
            ChatRoom newRoom = new ChatRoom();
            newRoom.setBuyer(buyer);
            newRoom.setSeller(seller);
            newRoom.setVehicle(vehicle);
            return chatRoomRepo.save(newRoom);
        });

        return ResponseEntity.ok(chatRoom.getId());
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody SendMessageRequest req, Authentication auth) {
        String username = auth.getName();
        UserEntity sender = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        ChatRoom room = chatRoomRepo.findById(req.getChatRoomId())
                .orElseThrow(() -> new RuntimeException("ChatRoom not found"));

        Message message = Message.builder()
                .chatRoom(room)
                .sender(sender)
                .content(req.getContent())
                .timestamp(LocalDateTime.now())
                .build();

        messageRepo.save(message);
        return ResponseEntity.ok("Message sent");
    }

    @GetMapping("/messages/{chatRoomId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable Long chatRoomId) {
        List<Message> messages = messageRepo.findByChatRoomIdOrderByTimestampAsc(chatRoomId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/my-chats")
    public ResponseEntity<List<ChatRoom>> getUserChats(Authentication auth) {
        String username = auth.getName();
        UserEntity user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<ChatRoom> rooms = chatRoomRepo.findByBuyerOrSeller(user, user);
        return ResponseEntity.ok(rooms);
    }
}
