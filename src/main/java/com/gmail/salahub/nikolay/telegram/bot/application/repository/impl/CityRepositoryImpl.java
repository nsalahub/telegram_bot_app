package com.gmail.salahub.nikolay.telegram.bot.application.repository.impl;

import com.gmail.salahub.nikolay.telegram.bot.application.repository.CityRepository;
import com.gmail.salahub.nikolay.telegram.bot.application.repository.model.City;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
public class CityRepositoryImpl extends GenericRepositoryImpl<Long, City> implements CityRepository {

    @Override
    public void deleteById(Long id) {
        String hqlQuery = "UPDATE City C SET C.isDeleted = true WHERE C.id =:id";
        Query query = entityManager.createQuery(hqlQuery);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public City findByTitle(String title) {
        String hqlQuery = "from City as C where C.title=:title";
        Query query = entityManager.createQuery(hqlQuery);
        query.setParameter("title", title);
        try {
            return (City) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
