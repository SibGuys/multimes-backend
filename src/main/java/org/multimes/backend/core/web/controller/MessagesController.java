package org.multimes.backend.core.web.controller;

import org.multimes.backend.core.tg.handler.TgHandler;
import org.multimes.backend.core.web.model.response.MessageResp;
import org.multimes.backend.core.web.service.interfaces.IMessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessagesController {
    private final IMessageService messageService;
    private final TgHandler tgHandler;

    public MessagesController(IMessageService messageService, TgHandler tgHandler) {
        this.messageService = messageService;
        this.tgHandler = tgHandler;
    }

    @GetMapping
    public List<MessageResp> getAllMessages() {
        return messageService.getAll();
    }

    @PostMapping
    public void sendMessage(@RequestBody MessageResp message) {
        messageService.addMessage(message);
        tgHandler.sendMessage(message);
    }

}
