/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model;

import static java.rmi.server.LogStream.log;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author sasha
 */
public class Vacancy implements IdMechanism, Comparable<Vacancy>{
    private Long id; //id pattern: 1* (starts with "1")
    private String title;
    private String description;
    private final Employer employer;
    private final LocalDateTime dateTimePosted;
    private TreeMap<Long, VacancyAnswer> answers = new TreeMap<>();
    private boolean openStatus = true;
    private final String idPattern = "^[1].+";
    final static DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public Vacancy(Long id, String title, String description, Employer employer) {
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

    public TreeMap<Long, VacancyAnswer> getAnswers() {
        return answers;
    }
    
    public VacancyAnswer addAnswer(Employee employee, String answer){
        Long id = answers.isEmpty() ? 1 : Long.valueOf(answers.size() + 1);
        VacancyAnswer vacancyAnswer = new VacancyAnswer(this, employee, answer);
        vacancyAnswer.createId(id);
        
        this.answers.put(vacancyAnswer.getId(), vacancyAnswer);
        return vacancyAnswer;
    }

    public void setOpenStatus(boolean openStatus) {
        this.openStatus = openStatus;
    }

    public LocalDateTime getDateTimePosted() {
        return dateTimePosted;
    }
    
    public String formatTime(){
        return dateTimePosted.format(CUSTOM_FORMATTER);
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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    public final void checkId(Long id) throws IllegalArgumentException{
        String actual = String.valueOf(id);
        
        if (!actual.matches(idPattern)){
            throw new IllegalArgumentException("Incorrect id pattern for Vacancy");
        }
    }

    @Override
    public final void createId(Long id) {
        Long creation = Long.valueOf("1" + this.employer.getId().toString() + id.toString());
        setId(creation);
    }
    
    public Float checkKeywords(String keywords){
        String search = keywords.toLowerCase().strip();
//        CharSequence chars = search.chars();
        Float coincidence = Float.valueOf(0);
        TreeMap<String, Float> parts = new TreeMap<>();
        parts.put(this.title.toLowerCase(), Float.valueOf(1));
        parts.put(this.description.toLowerCase(), 0.4f);
        parts.put(this.employer.getName().toLowerCase(), 1.5f);
        for(Map.Entry<String, Float> entry : parts.entrySet()){
            coincidence += entry.getValue().floatValue() * (entry.getKey().contains(search)?1:0);
        }
        
        return coincidence;
    }

    @Override
    public int compareTo(Vacancy o) {
        return this.getDateTimePosted().compareTo(o.getDateTimePosted());
    }
    
    
}
