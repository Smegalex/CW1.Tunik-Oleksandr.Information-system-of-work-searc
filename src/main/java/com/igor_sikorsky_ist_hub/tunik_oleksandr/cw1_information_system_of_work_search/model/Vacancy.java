/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model;

import java.time.LocalDateTime;
import java.util.TreeMap;

/**
 *
 * @author sasha
 */
public class Vacancy implements IdMechanism{
    private Integer id; //id pattern: 11* (starts with "11")
    private String title;
    private String description;
    private final Employer employer;
    private final LocalDateTime dateTimePosted;
    private TreeMap<Integer, VacancyAnswer> answers = new TreeMap<>();
    private boolean openStatus = true;
    private final String idPattern = "^[1]{2}.+";
    
    public Vacancy(Integer id, String title, String description, Employer employer) {
        checkId(id);
        this.id = id;
        this.title = title;
        this.description = description;
        this.employer = employer;
        
        this.dateTimePosted = LocalDateTime.now();
    }

    public Vacancy(String title, String description, Employer employer) {
        this.title = title;
        this.description = description;
        this.employer = employer;
        
        this.dateTimePosted = LocalDateTime.now();
    }

    public TreeMap<Integer, VacancyAnswer> getAnswers() {
        return answers;
    }
    
    public void addAnswer(Employee employee, String answer){
        Integer id = answers.isEmpty() ? 1 : answers.lastKey() + 1;
        VacancyAnswer vacancyAnswer = new VacancyAnswer(this, employee, answer);
        vacancyAnswer.createId(id);
        
        this.answers.put(id, vacancyAnswer);
    }

    public void setOpenStatus(boolean openStatus) {
        this.openStatus = openStatus;
    }

    public LocalDateTime getDateTimePosted() {
        return dateTimePosted;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        checkId(id);
        this.id = id;
    }

    public Employer getEmployer() {
        return employer;
    }

    public boolean isOpenStatus() {
        return openStatus;
    }
    
    @Override
    public final void checkId(Integer id) throws IllegalArgumentException{
        String actual = String.valueOf(id);
        
        if (actual.matches(idPattern)){
            throw new IllegalArgumentException("Incorrect id pattern for Vacancy");
        }
    }

    @Override
    public final void createId(Integer id) {
        Integer creation = Integer.valueOf("11" + this.employer.getId().toString() + id.toString());
        setId(creation);
    }
    
    public boolean checkKeywords(String keywords){
        String search = keywords.toLowerCase().strip();
        if(this.title.contains((CharSequence) search.chars())){
            return true;
        } 
        return this.description.contains((CharSequence) search.chars());
    }
    
    
}
