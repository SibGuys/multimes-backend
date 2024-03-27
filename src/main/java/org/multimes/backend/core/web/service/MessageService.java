package org.multimes.backend.core.web.service;

import java.util.ArrayList;
import java.util.List;

import org.multimes.backend.core.web.model.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    List<Message> messageList = new ArrayList<>();

    public List<Message> getMessages() {
        return messageList;
    }

    public void addMessage(Message message) {
        messageList.add(message);
    }
}
