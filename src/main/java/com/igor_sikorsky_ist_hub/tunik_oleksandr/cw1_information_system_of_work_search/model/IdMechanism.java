/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model;

/**
 *
 * @author sasha
 */
public interface IdMechanism {
    void checkId(Integer id) throws IllegalArgumentException;
    void createId(Integer id);
    Integer getId();
}
