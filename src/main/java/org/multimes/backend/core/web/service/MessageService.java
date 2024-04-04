package org.multimes.backend.core.web.service;

import org.multimes.backend.core.web.model.Message;
import org.multimes.backend.core.web.repository.interfaces.IMessageRepository;
import org.multimes.backend.core.web.service.interfaces.IMessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService implements IMessageService {

    private final IMessageRepository messageRepository;

    public MessageService(IMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void add(Message message) {
        messageRepository.add(message);
    }

    @Override
    public List<Message> getAll() {
        return messageRepository.getAll();
    }

}
