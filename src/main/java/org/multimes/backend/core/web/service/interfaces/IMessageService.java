package org.multimes.backend.core.web.service.interfaces;

import org.multimes.backend.core.web.model.response.MessageResp;

import java.util.List;

public interface IMessageService {
    List<MessageResp> getAll();

    public void addMessage(MessageResp message);
}
