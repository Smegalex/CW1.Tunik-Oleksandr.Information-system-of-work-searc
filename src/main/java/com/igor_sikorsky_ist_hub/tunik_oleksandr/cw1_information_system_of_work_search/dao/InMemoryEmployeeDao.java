/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.dao;

import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model.Employee;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author sasha
 */
public class InMemoryEmployeeDao implements EmployeeDao {

    private TreeMap<Integer, Employee> employees = new TreeMap<>();

    @Override
    public void create(Employee employee) {
        if (employee.getId() == null) {
            int id = employees.isEmpty() ? 1 : employees.lastKey() + 1;
            employee.createId(id);
        }
        employees.put(employee.getId(), employee);
    }

    @Override
    public Employee findById(Integer id) {
        return employees.get(id);
    }

    @Override
    public Employee findByLogin(String email) {
        for (Map.Entry<Integer, Employee> entry : employees.entrySet()) {
            Employee emp = entry.getValue();
            if (emp.getEmail().equals(email)) {
                return emp;
            }
        }
        return null;
    }

    @Override
    public Collection<Employee> findAll() {
        return employees.values();
    }

    @Override
    public void update(Employee employee) {
        employees.put(employee.getId(), employee);
    }

    @Override
    public void deleteById(Integer id) {
        employees.remove(id);
    }

}
