package com.gmail.salahub.nikolay.telegram.bot.application.repository;

import java.util.List;

public interface GenericRepository<I, T> {

    void persist(T entity);

    void merge(T entity);

    void remove(T entity);

    T findById(I id);

    @SuppressWarnings({"unchecked", "rawtypes"})
    List<T> findAll();

    int getCountOfEntities();
}
