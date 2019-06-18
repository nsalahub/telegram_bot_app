package com.gmail.salahub.nikolay.telegram.bot.application.service.converter.impl;

import com.gmail.salahub.nikolay.telegram.bot.application.repository.model.City;
import com.gmail.salahub.nikolay.telegram.bot.application.service.converter.CityConverter;
import com.gmail.salahub.nikolay.telegram.bot.application.service.model.CityDTO;
import org.springframework.stereotype.Component;

@Component("cityConverter")
public class CityConverterImpl implements CityConverter {

    @Override
    public City fromDTO(CityDTO cityDTO) {
        City city = new City();
        city.setId(cityDTO.getId());
        city.setDescription(cityDTO.getDescription());
        city.setTitle(cityDTO.getTitle());
        city.setDeleted(cityDTO.isDeleted());
        return city;
    }

    @Override
    public CityDTO toDTO(City city) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setDeleted(city.isDeleted());
        cityDTO.setTitle(city.getTitle());
        cityDTO.setDescription(city.getDescription());
        return cityDTO;
    }
}
