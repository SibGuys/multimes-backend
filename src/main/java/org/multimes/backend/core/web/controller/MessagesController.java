package org.multimes.backend.core.web.controller;

import org.multimes.backend.core.tg.handler.TgHandler;
import org.multimes.backend.core.web.model.Message;
import org.multimes.backend.core.web.model.response.MessageResp;
import org.multimes.backend.core.web.service.interfaces.IMessageService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        List<MessageResp> resps = new ArrayList<>();
        for (Message message : messageService.getAll()) {
            resps.add(new MessageResp("null", message.getText(), "null", true));
        }
        return resps;
    }

    @PostMapping
    public void sendMessage(@RequestBody MessageResp message) {
        messageService.add(new Message(message.getText(), 1));
        tgHandler.sendMessage(message);
    }

}
