package org.multimes.backend.core.web.controller;

import org.multimes.backend.core.tg.handler.TgHandler;
import org.multimes.backend.core.web.model.entities.Message;
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

    // @GetMapping
    // public List<MessageDTO> getAllMessages(@RequestBody int id) {
    //     return messageService.getAllByInterId(id);
    // }

    // @PostMapping
    // public void sendMessage(@RequestBody MessageDTO message) {
    //     Message m = new Message(-1, message.getText(), message.getMessageTime(), message.getIsInter(), 1);
    //     messageService.add(m);
    //     tgHandler.sendMessage(null);
    // }

}
