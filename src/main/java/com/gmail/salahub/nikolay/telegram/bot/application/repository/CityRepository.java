package com.gmail.salahub.nikolay.telegram.bot.application.repository;


import com.gmail.salahub.nikolay.telegram.bot.application.repository.model.City;

public interface CityRepository extends GenericRepository<Long, City> {
    void deleteById(Long id);

    City findByTitle(String title);
}
