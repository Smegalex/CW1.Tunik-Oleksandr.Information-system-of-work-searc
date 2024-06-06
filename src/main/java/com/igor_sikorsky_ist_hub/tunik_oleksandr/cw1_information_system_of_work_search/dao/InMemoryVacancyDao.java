/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.dao;

import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model.Vacancy;
import java.util.Collection;
import java.util.TreeMap;

/**
 *
 * @author sasha
 */
public class InMemoryVacancyDao implements VacancyDao{
    private TreeMap<Integer, Vacancy> vacancies = new TreeMap<>();

    @Override
    public void create(Vacancy employer) {
        if (employer.getId() == null) {
            int id = vacancies.isEmpty() ? 1 : vacancies.lastKey() + 1;
            employer.createId(id);
        }
        vacancies.put(employer.getId(), employer);
    }

    @Override
    public Vacancy findById(Integer id) {
        return vacancies.get(id);
    }

    @Override
    public Collection<Vacancy> findAll() {
        return vacancies.values();
    }

    @Override
    public void update(Vacancy employer) {
        vacancies.put(employer.getId(), employer);
    }

    @Override
    public void deleteById(Integer id) {
        vacancies.remove(id);
    }
}
