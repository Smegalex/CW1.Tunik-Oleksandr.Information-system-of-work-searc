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
public class Employee extends Profile implements IdMechanism {

    private Long id; //id pattern: 3* (starts with "3")
    private final String idPattern = "^[3].+";
    private TreeMap<Long, VacancyAnswer> answers = new TreeMap<>();

    public Employee(String name, String email) {
        super(name, email);
    }

    public Employee(Long id, String name, String email) {
        super(name, email);
        checkId(id);
        this.id = id;
    }

    public Employee(TreeMap<Long, VacancyAnswer> answers, String name, String email) {
        super(name, email);
        this.answers = answers;
    }

    public Employee(Long id, TreeMap<Long, VacancyAnswer> answers, String name, String email) {
        super(name, email);
        checkId(id);
        this.id = id;
        this.answers = answers;
    }

    @Override
    public final void checkId(Long id) throws IllegalArgumentException {
        String actual = String.valueOf(id);

        if (!actual.matches(idPattern)) {
            throw new IllegalArgumentException("Incorrect id pattern for Employee");
        }
    }

    public void setId(Long id) {
        checkId(id);
        this.id = id;
    }

    @Override
    public void createId(Long id) {
        Long creation = Long.valueOf("3" + id.toString());
        setId(creation);
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void addAnswer(VacancyAnswer answer) {
        if (answer.getId() == null) {
            Long id = answers.isEmpty() ? 1 : Long.valueOf(answers.size() + 1);
            answer.createId(id);
        }

        answers.put(answer.getId(), answer);
    }

    public TreeMap<Long, VacancyAnswer> getAnswers() {
        return answers;
    }

}
