package org.multimes.backend.core.web.repository;

import org.multimes.backend.core.web.model.response.MessageResp;
import org.multimes.backend.core.web.repository.interfaces.IMessageRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageRepository implements IMessageRepository {

    List<MessageResp> messageList = new ArrayList<>();

    @Override
    public void addMessage(MessageResp message) {
        messageList.add(message);
    }

    @Override
    public List<MessageResp> getAll() {
        return messageList;
    }

}
