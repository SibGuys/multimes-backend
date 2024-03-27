package org.multimes.backend.core.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.multimes.backend.core.web.model.Message;
import org.multimes.backend.core.web.service.InterService;
import org.multimes.backend.core.web.service.MessageService;
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

    private final MessageService messageService;
    private final InterService interService;

    public Updater(TelegramBot bot, MessageService messageService, InterService interService) {
        this.bot = bot;
        this.messageService = messageService;
        this.interService = interService;
    }

    private int oldMesId = -1;
    private long oldChatId = -1;

    @Scheduled(fixedRate = 1000)
    public void getUpdates() {
        GetUpdates getUpdates = new GetUpdates();
        bot.execute(getUpdates, new Callback<GetUpdates, GetUpdatesResponse>() {
            @Override
            public void onResponse(GetUpdates request, GetUpdatesResponse response) {
                List<Update> updates = response.updates();
                Update update = updates.get(updates.size() - 1);
                if (!updates.isEmpty()) {
                    int mesId = update.message().messageId();
                    long chatId = update.message().chat().id();
                    if (mesId != oldMesId) {
                        String id = String.valueOf(chatId);
                        String text = update.message().text();
                        messageService.addMessage(new Message(id, text, id, true));
                        oldMesId = mesId;
                    }
                    if (chatId != oldChatId) {
                        interService.addId(chatId);
                        oldChatId = chatId;
                    }
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
        Set<Long> list = interService.getInterIdList();
        if (!list.isEmpty()) {
            for (Long id : list) {
                bot.execute(new SendMessage(id, message.getText()));
            }
        }
    }
}
