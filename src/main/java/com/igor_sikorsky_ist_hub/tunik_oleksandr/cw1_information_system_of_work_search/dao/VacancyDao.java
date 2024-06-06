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
    //CRUD
    void create(Vacancy vacancy);
    Vacancy findById(Long id);
    Vacancy findByIdAndClosed(Long id);
    Collection<Vacancy> findAll();
    Collection<Vacancy> findAllAndClosed();
    void update(Vacancy vacancy);
    void deleteById(Long id);
}


