package org.multimes.backend.core.tg.handler;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import org.multimes.backend.core.web.model.Dialog;
import org.multimes.backend.core.web.model.response.MessageResp;
import org.multimes.backend.core.web.repository.interfaces.IDialogRepository;
import org.multimes.backend.core.web.service.interfaces.IMessageService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
public class TgHandler {
    private final TelegramBot bot;

    private final IMessageService messageService;
    private final IDialogRepository dialogRepository;

    public TgHandler(TelegramBot bot, IMessageService messageService, IDialogRepository dialogRepository) {
        this.bot = bot;
        this.messageService = messageService;
        this.dialogRepository = dialogRepository;
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
                    String username = update.message().chat().firstName() + " " + update.message().chat().lastName();
                    if (mesId != oldMesId) {
                        String id = String.valueOf(chatId);
                        String text = update.message().text();
                        messageService.addMessage(new MessageResp(id, text, id, true));
                        oldMesId = mesId;
                    }
                    dialogRepository.add(new Dialog(chatId, "telegram", username));
                } else {
                    System.out.println("LIST IS null");
                }
            }

            @Override
            public void onFailure(GetUpdates request, IOException e) {

            }
        });
    }

    public void sendMessage(MessageResp message) {
        Set<Dialog> set = dialogRepository.getAll();
        if (!set.isEmpty()) {
            for (Dialog dialog : set) {
                bot.execute(new SendMessage(dialog.getId(), message.getText()));
            }
        }
    }
}
