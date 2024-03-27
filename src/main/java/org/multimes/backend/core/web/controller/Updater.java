package org.multimes.backend.core.web.controller;

import java.io.IOException;
import java.util.List;

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

    public Updater(TelegramBot bot) {
        this.bot = bot;
    }

    public void getUpdates() {
        GetUpdates getUpdates = new GetUpdates();
        bot.execute(getUpdates, new Callback<GetUpdates, GetUpdatesResponse>() {
            @Override
            public void onResponse(GetUpdates request, GetUpdatesResponse response) {
                List<Update> updates = response.updates();
                if (updates != null) {
                    Update update = updates.get(updates.size() - 1);
                    System.out.println(update.message().chat().id());
                    System.out.println(update.message().text());
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
