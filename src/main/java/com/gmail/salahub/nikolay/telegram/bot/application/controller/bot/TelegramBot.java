package com.gmail.salahub.nikolay.telegram.bot.application.controller.bot;

import com.gmail.salahub.nikolay.telegram.bot.application.repository.model.City;
import com.gmail.salahub.nikolay.telegram.bot.application.service.CityService;
import com.gmail.salahub.nikolay.telegram.bot.application.service.model.CityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component("telegramBot")
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String botToken;

    @Autowired
    private CityService cityService;

    public void onUpdateReceived(Update update) {
        try {
            Message message = update.getMessage();
            SendMessage sendMessage = new SendMessage();
            if (message != null && message.hasText()) {
                CityDTO city = cityService.findByTitle(message.getText());
                if (city != null) {
                    String answer = city.getDescription();
                    sendMessage.setText(answer);
                } else {
                    sendMessage.setText("Sorry, there is no such city, try again");
                }
            }
            sendMessage.setChatId(update.getMessage().getChatId());
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return botName;
    }

    public String getBotToken() {
        return botToken;
    }
}
