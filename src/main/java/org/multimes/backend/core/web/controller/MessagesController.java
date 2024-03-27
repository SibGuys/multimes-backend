package org.multimes.backend.core.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.multimes.backend.core.web.model.Message;
import org.multimes.backend.core.web.service.MessageService;
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
    private final MessageService messageService;
    private final Updater updater;

    public MessagesController(MessageService messageService, Updater updater) {
        this.messageService = messageService;
        this.updater = updater;
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getMessages();
    }

    @PostMapping
    public void sendMessage(@RequestBody Message message) {
        messageService.addMessage(message);
        updater.sendMessage(message);
    }

}
