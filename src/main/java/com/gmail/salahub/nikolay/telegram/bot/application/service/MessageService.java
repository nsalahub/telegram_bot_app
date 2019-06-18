package com.gmail.salahub.nikolay.telegram.bot.application.service;

import com.gmail.salahub.nikolay.telegram.bot.application.service.model.CityDTO;

import java.util.List;

public interface MessageService {

    String getStringWithAllCities(List<CityDTO> cities);
}
