/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.dao;

import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model.Vacancy;
import java.util.Collection;

/**
 *
 * @author sasha
 */
public interface VacancyDao {
    void create(Vacancy vacancy);
    Vacancy findById(Integer id);
    Collection<Vacancy> findAll();
    void update(Vacancy vacancy);
    void deleteById(Integer id);
}


