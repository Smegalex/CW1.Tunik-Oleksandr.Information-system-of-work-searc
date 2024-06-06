/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.web;

import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.dao.EmployeeDao;
import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.dao.EmployerDao;
import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.dao.InMemoryEmployeeDao;
import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.dao.InMemoryEmployerDao;
import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.dao.InMemoryVacancyDao;
import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.dao.VacancyDao;
import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.util.TestDatabaseCreator;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 *
 * @author sasha
 */
@WebListener
public class VacanciesServletListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        VacancyDao vacancyDao = new InMemoryVacancyDao();
        EmployeeDao employeeDao = new InMemoryEmployeeDao();
        EmployerDao employerDao = new InMemoryEmployerDao();
        TestDatabaseCreator.createTestDatabaseCreator(vacancyDao, employeeDao, employerDao);
        sce.getServletContext().setAttribute("vacancyDao", vacancyDao);
        sce.getServletContext().setAttribute("employeeDao", employeeDao);
        sce.getServletContext().setAttribute("employerDao", employerDao);
        vacancyDao.findAll();
    }
}
