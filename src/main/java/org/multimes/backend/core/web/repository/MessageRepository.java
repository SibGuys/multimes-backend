package org.multimes.backend.core.web.repository;

import java.util.ArrayList;
import java.util.List;

import org.multimes.backend.core.web.model.Message;
import org.multimes.backend.core.web.repository.interfaces.IMessageRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepository implements IMessageRepository {

    List<Message> messageList = new ArrayList<>();

    @Override
    public void addMessage(Message message) {
        messageList.add(message);
    }

    @Override
    public List<Message> getAll() {
        return messageList;
    }

}
