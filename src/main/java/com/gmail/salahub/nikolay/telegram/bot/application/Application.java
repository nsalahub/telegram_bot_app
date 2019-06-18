package com.gmail.salahub.nikolay.telegram.bot.application;

import com.gmail.salahub.nikolay.telegram.bot.application.controller.bot.TelegramBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@SpringBootApplication(scanBasePackages = "com.gmail.salahub.nikolay.telegram.bot.application")
@EntityScan("com.gmail.salahub.nikolay.telegram.bot.application.repository.model")
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
        ApiContextInitializer.init();
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(context.getBean(TelegramBot.class));
        } catch (TelegramApiRequestException e) {
            logger.error(e.getMessage(), e);
        }
	}

}
