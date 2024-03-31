package org.multimes.backend.core.web.service.interfaces;

import java.util.List;

import org.multimes.backend.core.web.model.Message;

public interface IMessageService {
    List<Message> getAll();

    public void addMessage(Message message);
}
