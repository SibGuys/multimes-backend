package org.multimes.backend.core.web.service.interfaces;

import org.multimes.backend.core.web.model.Message;

import java.util.List;

public interface IMessageService {
    List<Message> getAll();

    public void add(Message message);
}
