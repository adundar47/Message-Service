package com.adundar.messageservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adundar.messageservice.exception.NotFoundException;
import com.adundar.messageservice.model.Message;
import com.adundar.messageservice.service.MessageService;

@RestController
@RequestMapping("/api/1.0")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping("/messages")
    public ResponseEntity<?> createMessage(@Valid @RequestBody Message message) throws NotFoundException {
        return ResponseEntity.ok(messageService.createMessage(message));
    }

    @GetMapping("/messages")
    public ResponseEntity<?> getAllMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<?> retrieveMessage(@PathVariable String messageId) throws NotFoundException {
        return ResponseEntity.ok(messageService.retrieveMessage(messageId));
    }

    @GetMapping("/messages/{userName}")
    public ResponseEntity<?> retrieveUserMessages(@PathVariable String userName) throws NotFoundException {
        return ResponseEntity.ok(messageService.retrieveUserMessages(userName));
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable String messageId) throws NotFoundException {
        return ResponseEntity.ok(messageService.deleteMessage(messageId));
    }

}
