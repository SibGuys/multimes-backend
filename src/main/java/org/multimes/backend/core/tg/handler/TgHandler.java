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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    private int offset = 0;

    @Scheduled(fixedRate = 1000)
    public void newGetUpdates() {
        GetUpdates getUpdates = new GetUpdates().offset(offset);
        bot.execute(getUpdates, new Callback<GetUpdates, GetUpdatesResponse>() {
            @Override
            public void onResponse(GetUpdates request, GetUpdatesResponse response) {
                List<Update> updates = response.updates();
                if (updates != null) {
                    for (int i = 0; i < updates.size(); ++i) {
                        Update update = updates.get(i);
                        int mesId = update.message().messageId();
                        long chatId = update.message().chat().id();
                        String username = update.message().chat().firstName() + " "
                                + update.message().chat().lastName();
                        if (mesId != oldMesId) {
                            String text = update.message().text();
                            if (text == null || text.isEmpty()) {
                                text = "[UNSUPPORTED_FORMAT]";
                            }
                            String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString();
                            MessageResp message = new MessageResp(username, text, time, true);
                            messageService.addMessage(message);
                            oldMesId = mesId;
                        }
                        dialogRepository.add(new Dialog(chatId, "telegram", username));
                        if (i == updates.size() - 1) {
                            offset = update.updateId() + 1;
                        }
                    }
                } else {
                    System.out.println("Updates is null");
                }
            }

            @Override
            public void onFailure(GetUpdates request, IOException e) {
                System.out.println(e.getMessage());
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
