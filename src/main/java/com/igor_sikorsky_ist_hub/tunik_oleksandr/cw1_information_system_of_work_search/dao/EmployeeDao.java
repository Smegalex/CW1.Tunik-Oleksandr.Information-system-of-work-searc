/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.dao;

import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model.Employee;
import java.util.Collection;

/**
 *
 * @author sasha
 */
public interface EmployeeDao {
    //CRUD
    void create(Employee employee);
    Employee findById(Integer id);
    Employee findByLogin(String email);
    Collection<Employee> findAll();
    void update(Employee employee);
    void deleteById(Integer id);
}
