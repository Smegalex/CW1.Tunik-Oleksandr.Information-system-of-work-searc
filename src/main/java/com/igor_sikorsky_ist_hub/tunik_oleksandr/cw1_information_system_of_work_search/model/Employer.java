/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model;

import java.util.TreeMap;

/**
 *
 * @author sasha
 */
public class Employer extends Profile implements IdMechanism {

    private Integer id; //id pattern: * (starts with "4")
    private final String idPattern = "^[4].+";
    private TreeMap<Integer, Vacancy> postedVacancies;

    public Employer(String name, String email) {
        super(name, email);
    }

    public Employer(Integer id, String name, String email) {
        super(name, email);
        checkId(id);
        this.id = id;
    }

    public Employer(TreeMap<Integer, Vacancy> postedVacancies, String name, String email) {
        super(name, email);
        this.postedVacancies = postedVacancies;
    }

    public Employer(Integer id, TreeMap<Integer, Vacancy> postedVacancies, String name, String email) {
        super(name, email);
        checkId(id);
        this.id = id;
        this.postedVacancies = postedVacancies;
    }

    @Override
    public final void checkId(Integer id) throws IllegalArgumentException {
        String actual = String.valueOf(id);

        if (!actual.matches(idPattern)) {
            throw new IllegalArgumentException("Incorrect id pattern for Employer");
        }
    }

    public void setId(Integer id) {
        checkId(id);
        this.id = id;
    }

    public void addVacancy(Vacancy vacancy) {
        if (vacancy.getId() == null) {
            Integer id = postedVacancies.isEmpty() ? 1 : postedVacancies.lastKey() + 1;
            vacancy.createId(id);
        }

        postedVacancies.put(vacancy.getId(), vacancy);
    }

    public TreeMap<Integer, Vacancy> getPostedVacancies() {
        return postedVacancies;
    }

    @Override
    public void createId(Integer id) {
        Integer creation = Integer.valueOf("4" + id.toString());
        setId(creation);
    }

    @Override
    public Integer getId() {
        return this.id;
    }
}
