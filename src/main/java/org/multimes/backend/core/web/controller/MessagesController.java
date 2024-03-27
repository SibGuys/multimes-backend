package org.multimes.backend.core.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.multimes.backend.core.web.model.Message;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/messages")
public class MessagesController {
    List<Message> messageList = new ArrayList<>();

    @GetMapping
    public List<Message> getAllMessages() {
        return messageList;
    }

    @PostMapping
    public void sendMessage(@RequestBody Message message) {
        messageList.add(message);
    }

}
