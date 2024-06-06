/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model;

/**
 *
 * @author sasha
 */
public class Employee extends Profile implements IdMechanism {

    private Integer id; //id pattern: 3* (starts with "3")
    private final String idPattern = "^[3].+";

    public Employee(String name, String email) {
        super(name, email);
    }

    public Employee(Integer id, String name, String email) {
        super(name, email);
        checkId(id);
        this.id = id;
    }

    @Override
    public final void checkId(Integer id) throws IllegalArgumentException {
        String actual = String.valueOf(id);

        if (!actual.matches(idPattern)) {
            throw new IllegalArgumentException("Incorrect id pattern for Employee");
        }
    }

    public void setId(Integer id) {
        checkId(id);
        this.id = id;
    }

    @Override
    public void createId(Integer id) {
        Integer creation = Integer.valueOf("3" + id.toString());
        setId(creation);
    }

    @Override
    public Integer getId() {
        return this.id;
    }
}
