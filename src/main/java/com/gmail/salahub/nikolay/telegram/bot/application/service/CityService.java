package com.gmail.salahub.nikolay.telegram.bot.application.service;



import com.gmail.salahub.nikolay.telegram.bot.application.repository.model.City;
import com.gmail.salahub.nikolay.telegram.bot.application.service.model.CityDTO;

import java.util.List;

public interface CityService {
    List<CityDTO> getAll();

    void create(CityDTO cityDTO);

    void delete(Long id);

    void update(CityDTO cityDTO);

    CityDTO findByTitle(String title);
}
