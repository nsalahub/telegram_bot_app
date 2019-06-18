package com.gmail.salahub.nikolay.telegram.bot.application.service.impl;

import com.gmail.salahub.nikolay.telegram.bot.application.service.MessageService;
import com.gmail.salahub.nikolay.telegram.bot.application.service.model.CityDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private static final String DEFAULT_MESSAGE_AFTER_TRANSFER_OF_CITIES = "Existing cities: ";

    @Override
    public String getStringWithAllCities(List<CityDTO> cities) {
        String result = DEFAULT_MESSAGE_AFTER_TRANSFER_OF_CITIES;
        for (CityDTO city : cities) {
            result = result + city.getTitle() + " ";
        }
        return result;
    }
}
