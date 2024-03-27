package org.multimes.backend.core.web.controller;

import java.io.IOException;
import java.util.List;

import org.multimes.backend.core.web.model.Message;
import org.multimes.backend.core.web.service.MessageService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

@Component
public class Updater {
    private final TelegramBot bot;

    private final MessageService messageService;

    public Updater(TelegramBot bot, MessageService messageService) {
        this.bot = bot;
        this.messageService = messageService;
    }

    private int oldId = -1;

    @Scheduled(fixedRate = 1000)
    public void getUpdates() {
        GetUpdates getUpdates = new GetUpdates();
        bot.execute(getUpdates, new Callback<GetUpdates, GetUpdatesResponse>() {
            @Override
            public void onResponse(GetUpdates request, GetUpdatesResponse response) {
                List<Update> updates = response.updates();
                Update update = updates.get(updates.size() - 1);
                int mesId = update.message().messageId();
                if (mesId != oldId) {
                    String id = String.valueOf(update.message().chat().id());
                    String text = update.message().text();
                    messageService.addMessage(new Message(id, text, id, true));
                    oldId = mesId;
                } else {
                    System.out.println("LIST IS null");
                }
            }

            @Override
            public void onFailure(GetUpdates request, IOException e) {

            }
        });
    }
}
