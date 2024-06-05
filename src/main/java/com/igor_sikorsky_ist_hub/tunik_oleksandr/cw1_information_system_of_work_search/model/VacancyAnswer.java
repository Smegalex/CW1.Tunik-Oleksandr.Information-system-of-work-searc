/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model;

import java.time.LocalDateTime;

/**
 *
 * @author sasha
 */
public class VacancyAnswer implements IdMechanism {

    private Integer id;
    private final Vacancy vacancy;
    private final Employee employee;
    private final LocalDateTime dateTimeAnswer;
    private final String answer;
    private final String idPattern = "^[1][2].+"; //id pattern: 12[vacancyId][employeeId][id] (starts with "12")

    public VacancyAnswer(Integer id, Vacancy vacancy, Employee employee, String answer) {
        checkId(id);
        this.id = id;
        this.vacancy = vacancy;
        this.employee = employee;
        this.answer = answer;

        this.dateTimeAnswer = LocalDateTime.now();
    }

    public VacancyAnswer(Vacancy vacancy, Employee employee, String answer) {
        this.vacancy = vacancy;
        this.employee = employee;
        this.answer = answer;

        this.dateTimeAnswer = LocalDateTime.now();
    }

    @Override
    public void createId(Integer id) {
        Integer creation = Integer.valueOf("12" + this.vacancy.getId().toString() + this.employee.getId().toString() + id.toString());
        setId(creation);
    }

    public void setId(Integer id) {
        checkId(id);
        this.id = id;
    }
    
    @Override
    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTimeAnswer() {
        return dateTimeAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public final void checkId(Integer id) throws IllegalArgumentException {
        String actual = String.valueOf(id);

        if (actual.matches(idPattern)) {
            throw new IllegalArgumentException("Incorrect id pattern for VacancyAnswer");
        }
    }

}
