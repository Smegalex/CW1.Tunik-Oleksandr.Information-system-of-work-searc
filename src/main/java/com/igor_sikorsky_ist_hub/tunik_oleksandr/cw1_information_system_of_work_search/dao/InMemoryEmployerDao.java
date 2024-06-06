/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.dao;

import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model.Employer;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author sasha
 */
public class InMemoryEmployerDao implements EmployerDao {

    private TreeMap<Long, Employer> employers = new TreeMap<>();

    @Override
    public void create(Employer employer) {
        if (employer.getId() == null) {
            long id = employers.isEmpty() ? 1 : employers.size() + 1;
            employer.createId(id);
        }
        employers.put(employer.getId(), employer);
    }

    @Override
    public Employer findById(Long id) {
        return employers.get(id);
    }
    
    @Override
    public Employer findByLogin(String email) {
        for (Map.Entry<Long, Employer> entry : employers.entrySet()) {
            Employer emp = entry.getValue();
            if (emp.getEmail().equals(email)) {
                return emp;
            }
        }
        return null;
    }

    @Override
    public Collection<Employer> findAll() {
        return employers.values();
    }

    @Override
    public void update(Employer employer) {
        employers.put(employer.getId(), employer);
    }

    @Override
    public void deleteById(Long id) {
        employers.remove(id);
    }
}
