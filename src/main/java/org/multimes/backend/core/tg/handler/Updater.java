package org.multimes.backend.core.tg.handler;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.multimes.backend.core.web.model.Message;
import org.multimes.backend.core.web.service.interfaces.IInterService;
import org.multimes.backend.core.web.service.interfaces.IMessageService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

@Component
public class Updater {
    private final TelegramBot bot;

    private final IMessageService messageService;
    private final IInterService interService;

    public Updater(TelegramBot bot, IMessageService messageService, IInterService interService) {
        this.bot = bot;
        this.messageService = messageService;
        this.interService = interService;
    }

    private int oldMesId = -1;

    @Scheduled(fixedRate = 1000)
    public void getUpdates() {
        GetUpdates getUpdates = new GetUpdates();
        bot.execute(getUpdates, new Callback<GetUpdates, GetUpdatesResponse>() {
            @Override
            public void onResponse(GetUpdates request, GetUpdatesResponse response) {
                List<Update> updates = response.updates();
                if (!updates.isEmpty()) {
                    Update update = updates.get(updates.size() - 1);
                    int mesId = update.message().messageId();
                    long chatId = update.message().chat().id();
                    if (mesId != oldMesId) {
                        String id = String.valueOf(chatId);
                        String text = update.message().text();
                        messageService.addMessage(new Message(id, text, id, true));
                        oldMesId = mesId;
                    }
                    interService.addId(chatId);
                } else {
                    System.out.println("LIST IS null");
                }
            }

            @Override
            public void onFailure(GetUpdates request, IOException e) {

            }
        });
    }

    public void sendMessage(Message message) {
        Set<Long> list = interService.getAll();
        if (!list.isEmpty()) {
            for (Long id : list) {
                bot.execute(new SendMessage(id, message.getText()));
            }
        }
    }
}
