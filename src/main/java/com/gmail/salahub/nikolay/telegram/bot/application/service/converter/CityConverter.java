package com.gmail.salahub.nikolay.telegram.bot.application.service.converter;

import com.gmail.salahub.nikolay.telegram.bot.application.repository.model.City;
import com.gmail.salahub.nikolay.telegram.bot.application.service.model.CityDTO;

public interface CityConverter {

    City fromDTO(CityDTO cityDTO);

    CityDTO toDTO(City city);
}
