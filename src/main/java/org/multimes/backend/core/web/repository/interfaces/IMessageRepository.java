package org.multimes.backend.core.web.repository.interfaces;

import org.multimes.backend.core.web.model.Message;

import java.util.List;

public interface IMessageRepository {
    public void add(Message message);
    
    public Message getById(int id);

    List<Message> getAll();
}
