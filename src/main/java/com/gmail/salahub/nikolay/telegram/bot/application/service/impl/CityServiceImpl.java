package com.gmail.salahub.nikolay.telegram.bot.application.service.impl;

import com.gmail.salahub.nikolay.telegram.bot.application.repository.CityRepository;
import com.gmail.salahub.nikolay.telegram.bot.application.repository.model.City;
import com.gmail.salahub.nikolay.telegram.bot.application.service.CityService;
import com.gmail.salahub.nikolay.telegram.bot.application.service.converter.CityConverter;
import com.gmail.salahub.nikolay.telegram.bot.application.service.exception.NoResultCityServiceException;
import com.gmail.salahub.nikolay.telegram.bot.application.service.model.CityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service("cityService")
public class CityServiceImpl implements CityService {

    private static final String RETURNING_NULL_FROM_REPOSITORY_ERROR_MESSAGE =
            "City Repository did not return anything";

    private final CityConverter cityConverter;
    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityConverter cityConverter,
                           CityRepository cityRepository) {
        this.cityConverter = cityConverter;
        this.cityRepository = cityRepository;
    }

    @Override
    @Transactional
    public List<CityDTO> getAll() {
        List<City> cities = cityRepository.findAll();
        return cities.stream()
                .map(cityConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void create(CityDTO cityDTO) {
        City city = cityConverter.fromDTO(cityDTO);
        cityRepository.persist(city);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(CityDTO cityDTO) {
        City city = cityConverter.fromDTO(cityDTO);
        cityRepository.merge(city);
    }


    @Override
    @Transactional
    public CityDTO findByTitle(String title) {
        City user = cityRepository.findByTitle(title);
        if (user != null) {
            return cityConverter.toDTO(user);
        } else {
            throw new NoResultCityServiceException(RETURNING_NULL_FROM_REPOSITORY_ERROR_MESSAGE);
        }
    }
}
