/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model;

import static com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model.Vacancy.CUSTOM_FORMATTER;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author sasha
 */
public class VacancyAnswer implements IdMechanism {

    private Long id;
    private final Vacancy vacancy;
    private final Employee employee;
    private final LocalDateTime dateTimeAnswer;
    private final String answer;
    private final String idPattern = "^[2].+"; //id pattern: 2[vacancyId][employeeId][id] (starts with "2")
    final static DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public VacancyAnswer(Long id, Vacancy vacancy, Employee employee, String answer) {
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
    public void createId(Long id) {
        Long creation = Long.valueOf("2" + this.employee.getId().toString() + this.vacancy.getId().toString() + id.toString());
        setId(creation);
    }

    public void setId(Long id) {
        checkId(id);
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public LocalDateTime getDateTimeAnswer() {
        return dateTimeAnswer;
    }

    public String formatTime() {
        return dateTimeAnswer.format(CUSTOM_FORMATTER);
    }

    public String getAnswer() {
        return answer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    @Override
    public final void checkId(Long id) throws IllegalArgumentException {
        String actual = String.valueOf(id);

        if (!actual.matches(idPattern)) {
            throw new IllegalArgumentException("Incorrect id pattern for VacancyAnswer");
        }
    }

}
