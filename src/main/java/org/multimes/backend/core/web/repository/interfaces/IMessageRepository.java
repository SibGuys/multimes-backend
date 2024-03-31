package org.multimes.backend.core.web.repository.interfaces;

import java.util.List;

import org.multimes.backend.core.web.model.Message;

public interface IMessageRepository {
    List<Message> getAll();

    public void addMessage(Message message);
}
