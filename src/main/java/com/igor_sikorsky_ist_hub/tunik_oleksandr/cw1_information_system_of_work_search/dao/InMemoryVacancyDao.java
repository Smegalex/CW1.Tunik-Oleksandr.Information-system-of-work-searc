/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.dao;

import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model.Vacancy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author sasha
 */
public class InMemoryVacancyDao implements VacancyDao {

    private TreeMap<Long, Vacancy> vacancies = new TreeMap<>();

    @Override
    public void create(Vacancy vacancy) {
        if (vacancy.getId() == null) {
            long id = vacancies.isEmpty() ? 1 : vacancies.size() + 1;
            vacancy.createId(id);
        }
        vacancies.put(vacancy.getId(), vacancy);
    }

    @Override
    public Vacancy findById(Long id) {
        if (vacancies.get(id).isOpenStatus()) {
            return vacancies.get(id);
        }
        return null;
    }

    @Override
    public Vacancy findByIdAndClosed(Long id) {
        return vacancies.get(id);
    }

    @Override
    public Collection<Vacancy> findAll() {
        Collection<Vacancy> returnable = new ArrayList<>();
        for (Map.Entry<Long, Vacancy> entry : vacancies.entrySet()) {
            if (entry.getValue().isOpenStatus()) {
                returnable.add(entry.getValue());
            }
        }
        return returnable;
    }

    @Override
    public Collection<Vacancy> findAllAndClosed() {
        return vacancies.values();
    }

    @Override
    public void update(Vacancy vacancy) {
        vacancies.put(vacancy.getId(), vacancy);
    }

    @Override
    public void deleteById(Long id) {
        vacancies.remove(id);
    }
}
