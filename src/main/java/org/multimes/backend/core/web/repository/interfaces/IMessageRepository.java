package org.multimes.backend.core.web.repository.interfaces;

import org.multimes.backend.core.web.model.response.MessageResp;

import java.util.List;

public interface IMessageRepository {
    List<MessageResp> getAll();

    public void addMessage(MessageResp message);
}
