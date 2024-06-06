/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.dao;

import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model.Employer;
import java.util.Collection;

/**
 *
 * @author sasha
 */
public interface EmployerDao {
    //CRUD
    void create(Employer employer);
    Employer findById(Integer id);
    Employer findByLogin(String login);
    Collection<Employer> findAll();
    void update(Employer employer);
    void deleteById(Integer id);
}
