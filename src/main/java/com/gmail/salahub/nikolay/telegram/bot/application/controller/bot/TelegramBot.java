package com.gmail.salahub.nikolay.telegram.bot.application.controller.bot;

import com.gmail.salahub.nikolay.telegram.bot.application.service.CityService;
import com.gmail.salahub.nikolay.telegram.bot.application.service.MessageService;
import com.gmail.salahub.nikolay.telegram.bot.application.service.exception.NoResultCityServiceException;
import com.gmail.salahub.nikolay.telegram.bot.application.service.model.CityDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

import static com.gmail.salahub.nikolay.telegram.bot.application.controller.bot.constant.CommandConstant.SHOW_ALL_CITIES_COMMAND;

@Component("telegramBot")
public class TelegramBot extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String botToken;

    private final CityService cityService;
    private final MessageService messageService;

    @Autowired
    public TelegramBot(CityService cityService,
                       MessageService messageService) {
        this.cityService = cityService;
        this.messageService = messageService;
    }

    public void onUpdateReceived(Update update) {
        try {
            Message message = update.getMessage();
            SendMessage sendMessage = new SendMessage();
            if (message != null && message.hasText()) {
                if (message.getText().equals(SHOW_ALL_CITIES_COMMAND)) {
                    printAllCities(sendMessage);
                } else {
                    sendMessageByTitle(message.getText(), message, sendMessage);
                }
            }
            sendMessage.setChatId(update.getMessage().getChatId());
            execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public String getBotUsername() {
        return botName;
    }

    public String getBotToken() {
        return botToken;
    }

    private void printAllCities(SendMessage sendMessage) {
        List<CityDTO> existingCities = cityService.getAll();
        if (existingCities.isEmpty()) {
            sendMessage.setText("City list is empty");
        }
        String answer = messageService.getStringWithAllCities(existingCities);
        sendMessage.setText(answer);
    }

    private void sendMessageByTitle(String title, Message message, SendMessage sendMessage) {
        try {
            CityDTO city = cityService.findByTitle(message.getText());
            if (city != null) {
                String answer = city.getDescription();
                sendMessage.setText(answer);
            } else {
                sendMessage.setText("Sorry, there is no such city, try again");
            }
        } catch (NoResultCityServiceException e) {
            sendMessage.setText("Sorry, there is no such city, try again");
        }
    }
}
