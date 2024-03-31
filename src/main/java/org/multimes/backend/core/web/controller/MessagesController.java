package org.multimes.backend.core.web.controller;

import java.util.List;

import org.multimes.backend.core.tg.handler.Updater;
import org.multimes.backend.core.web.model.Message;
import org.multimes.backend.core.web.service.interfaces.IMessageService;
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
    private final IMessageService messageService;
    private final Updater updater;

    public MessagesController(IMessageService messageService, Updater updater) {
        this.messageService = messageService;
        this.updater = updater;
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAll();
    }

    @PostMapping
    public void sendMessage(@RequestBody Message message) {
        messageService.addMessage(message);
        updater.sendMessage(message);
    }

}
